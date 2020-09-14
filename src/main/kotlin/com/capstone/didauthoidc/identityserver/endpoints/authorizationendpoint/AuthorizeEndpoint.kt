package com.capstone.didauthoidc.identityserver.endpoints.authorizationendpoint

import com.capstone.didauthoidc.IdentityConstants
import org.springframework.util.MultiValueMap
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RequestParam
import com.capstone.didauthoidc.acapy.ACAPYClient
import com.capstone.didauthoidc.acapy.models.CreatePresentationResponse
import com.capstone.didauthoidc.acapy.models.WalletPublicDid
import com.capstone.didauthoidc.models.PresentationRequestMessage
import com.capstone.didauthoidc.models.ServiceDecorator
import com.capstone.didauthoidc.services.UrlShortenerService
import com.capstone.didauthoidc.utils.OurJacksonObjectMapper
import com.capstone.didauthoidc.utils.PresentationRequestUtils
import com.fasterxml.jackson.module.kotlin.readValue
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import java.util.Base64
import kotlin.NoSuchElementException

@Controller
@RequestMapping("/vc/connect/authorize")
class AuthorizeEndpoint {

    companion object {
        const val Name = "VCAuthorize"
    }

    // 일반 base64로 인코딩된 presentationRequest를 가지고 있는 url을 더 짧은 url로 바꿔주기 위해 필요한 객체.
    val urlShortenerService = UrlShortenerService()

    @RequestMapping(method = arrayOf(RequestMethod.POST, RequestMethod.GET))
    fun ProcessAsync(@RequestParam param: MultiValueMap<String, String>, model: Model): String {

        val scopes: List<String> = param.getValue("scope")[0].toString().split(" ")

        var isVcauthnInScope = 0

        for (scope in scopes) {
            if (scope == "vc_authn")
                isVcauthnInScope = 1
        }
        if (isVcauthnInScope == 0) {
            return error("scopes doesn't contain vc_authn")
        }

        var presentationRecordId: String = param.getValue("pres_req_conf_id").toString()

        var redirectUrl: String = param.getValue("redirect_uri").toString()

        var responseType: String

        try {
            responseType = param.getValue("response_type").toString()
        } catch (e: NoSuchElementException) {
            responseType = IdentityConstants.DefaultResponseType
        }

        var responseMode: String

        try {
            responseMode = param.getValue(IdentityConstants.ResponseModeUriParameterName).toString()
        } catch (e: NoSuchElementException) {
            responseMode = IdentityConstants.DefaultResponseMode
        }

        var aca = ACAPYClient()
//        var acapyPublicDid: WalletPublicDid = aca.WalletDidPublic()

        // 도커에 acapy를 안띄웠을경우를 대비한 디버깅용 코드
        var acapyPublicDid =
            WalletPublicDid("Th7MpTaRZVRYnPiabds81Y", "FYmoFw55GeQH7SRFa37dkx1d2dZ3zUF8ckg7wmL7ofN4", true)

        // 다음은 PresentationRequestMessage 객체를 생성해야 한다.
        var presentationRequest: PresentationRequestMessage
        var presentationRequestId: String

        // 이 부분이 acapy와 통신하여 response를 받아오는 부분이다. 우선 하드코딩으로 채워넣자.
//        var response = _acapyClient.createPresentationRequestAsync(presentationRecord.configuration)
        var responseAsString: String = "{\n" +
            "    \"thread_id\":\"6ae21fe7-f3e0-4ffe-98a8-a971056ac662\",\n" +
            "    \"presentation_exchange_id\":\"a88d9a79-4b39-4075-9a35-d01d621dbf86\",\n" +
            "    \"presentation_request\":{\n" +
            "        \"name\":\"Basic Proof\",\n" +
            "        \"version\":\"1.0\",\n" +
            "        \"nonce\":\"36274825438457821717167\",\n" +
            "        \"requested_attributes\":{\n" +
            "            \"f0e6544d-0efd-4b9a-9906-8a9255b00968\":{\n" +
            "                \"name\":\"email\",\n" +
            "                \"restrictions\":[\n" +
            "                    \n" +
            "                ]\n" +
            "            },\n" +
            "            \"b0b29f57-d2d5-4b6e-b3f7-6a7e1322f653\":{\n" +
            "                \"name\":\"first_name\",\n" +
            "                \"restrictions\":[\n" +
            "                    \n" +
            "                ]\n" +
            "            },\n" +
            "            \"b5db4150-2eeb-4ba0-8f87-fae0e3de5486\":{\n" +
            "                \"name\":\"last_name\",\n" +
            "                \"restrictions\":[\n" +
            "                    \n" +
            "                ]\n" +
            "            }\n" +
            "        },\n" +
            "        \"requested_predicates\":{\n" +
            "            \n" +
            "        }\n" +
            "    }\n" +
            "}"

        // 방금 위에서 선언한 responseAsString을 역직렬화하여 변수 response에 저장한다.
        var response: CreatePresentationResponse = OurJacksonObjectMapper.getMapper().readValue(responseAsString)
        presentationRequest = buildPresentationRequest(response, acapyPublicDid, aca)
        presentationRequestId = response.PresentationExchangeId

        // 다음으로 url과 shortUrl을 만들자.
        val presentationRequestAsString = OurJacksonObjectMapper.getMapper().writeValueAsString(presentationRequest)

        val presentationRequestAsStringAsBase64 = Base64.getEncoder()
            .encodeToString(presentationRequestAsString.toByteArray())

        val url = "http://localhost:5000?m=$presentationRequestAsStringAsBase64"
        var shortUrl: String = urlShortenerService.createShortUrl(url)

        // TODO : 다음은 AuthSession 데이터 클래스를 이용해 세션을 DB에 저장해야 한다. but 지금은 세션처리를 안하겠다.

        // 리턴하는것은 thymeleaf로 구현된 qr코드 화면이다.
        return AuthorizationEndpointResult(
            AuthorizationViewModel(
                shortUrl,
                "http://localhost:5000/vc/connect/poll?pid=$presentationRequestId",
                "http://localhost:5000/vc/connect/callback?pid=$presentationRequestId",
                presentationRequestAsString
            )
        ).ExecuteAsnc(model)
    }

    // 이 메소드는 CreatePresentationResponse 객체로 부터 필요한 정보를 파싱해 PresentationRequestMessage 객체 형태로 만들어준다.
    fun buildPresentationRequest(
        response: CreatePresentationResponse,
        acapyPublicDid: WalletPublicDid,
        aca: ACAPYClient
    ): PresentationRequestMessage {

        var request = PresentationRequestMessage()

        request.id = response.ThreadId
        request.request = PresentationRequestUtils.generatePresentationAttachments(response.PresentationRequest)

        var service = ServiceDecorator()

        service.recipientKeys.add(acapyPublicDid.Verkey)
        service.serviceEndpoint = aca.GetAgentUrl()!!

        request.service = service

        return request
    }
}
