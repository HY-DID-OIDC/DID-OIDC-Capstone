package com.capstone.didauthoidc.identityserver.endpoints.authorizationendpoint

import com.capstone.didauthoidc.Authsession_constant
import com.capstone.didauthoidc.UrlConstant
import com.capstone.didauthoidc.acapy.ACAPYClient
import com.capstone.didauthoidc.acapy.models.CreatePresentationResponse
import com.capstone.didauthoidc.acapy.models.WalletPublicDid
import com.capstone.didauthoidc.identityserver.IdentityConstants
import com.capstone.didauthoidc.models.RequestedAttribute
import com.capstone.didauthoidc.models.AttributeFilter
import com.capstone.didauthoidc.models.PresentationRequestMessage
import com.capstone.didauthoidc.models.PresentationRequestConfiguration
import com.capstone.didauthoidc.models.PresentationConfiguration
import com.capstone.didauthoidc.models.ServiceDecorator
import com.capstone.didauthoidc.services.UrlShortenerService
import com.capstone.didauthoidc.utils.OurJacksonObjectMapper
import com.capstone.didauthoidc.utils.PresentationRequestUtils
import com.fasterxml.jackson.annotation.JsonAutoDetect
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.util.MultiValueMap
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RequestParam
import java.util.Base64
import java.util.UUID
import javax.servlet.http.HttpServletRequest
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
    fun processAsync(@RequestParam param: MultiValueMap<String, String>, model: Model, req: HttpServletRequest): String {

        Authsession_constant.issued_at = "https://${req.requestURL.toString().split("/vc")[0].split("//")[1]}"

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
        Authsession_constant.presentationRecordId = presentationRecordId

        var nonce: String = param.getValue("nonce").toString().split("[")[1].split("]")[0]

        Authsession_constant.nonce = nonce

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

        var acapyPublicDid: WalletPublicDid = aca.walletPublicDid()

        val mapper = ObjectMapper()
        mapper.visibilityChecker = mapper.serializationConfig.defaultVisibilityChecker
            .withFieldVisibility(JsonAutoDetect.Visibility.ANY)
            .withGetterVisibility(JsonAutoDetect.Visibility.NONE)
            .withSetterVisibility(JsonAutoDetect.Visibility.NONE)
            .withCreatorVisibility(JsonAutoDetect.Visibility.NONE)

        val attributeFilter = AttributeFilter(issuerDid = "MTYqmTBoLT7KLP5RNfgK3b", schemaName = "verified-email")

        val requestedAttribute1 = RequestedAttribute("email")
        requestedAttribute1.restrictions?.add(attributeFilter)

        val presentationRequestConfiguration = PresentationRequestConfiguration("verified-email", "1.0")
        presentationRequestConfiguration.RequestedAttributes.add(requestedAttribute1)

        val presentationConfiguration = PresentationConfiguration("test-request-config", "email", null, presentationRequestConfiguration)

        // 다음은 PresentationRequestMessage 객체를 생성해야 한다.
        var presentationRequest: PresentationRequestMessage
        var presentationRequestId: String

        // 이 부분이 acapy와 통신하여 response를 받아오는 부분이다. 우선 하드코딩으로 채워넣자.
        var response = aca.CreatePresentationRequestAsync(presentationRequestConfiguration)

        // 방금 위에서 선언한 responseAsString을 역직렬화하여 변수 response에 저장한다.

        presentationRequest = buildPresentationRequest(response, acapyPublicDid, aca)
        presentationRequestId = response.presentationExchangeId!!

        // 다음으로 url과 shortUrl을 만들자.
        val presentationRequestAsString = OurJacksonObjectMapper.getMapper().writeValueAsString(presentationRequest)

        val presentationRequestAsStringAsBase64 = Base64.getEncoder()
            .encodeToString(presentationRequestAsString.toByteArray())

        val url = "${req.requestURL}" + "?m=$presentationRequestAsStringAsBase64"

        UrlConstant.longUrl = url
        Authsession_constant.session_Id = UUID.randomUUID().toString()
        Authsession_constant.state = param.getValue("state")[0].toString()

        val modified_url = url.split("/vc")[0].split("//")

        val header_url = "https://"

        val body_url = modified_url[1]

        val final_url = header_url + body_url

        var shortUrl: String = urlShortenerService.createShortUrl(final_url)

        // TODO : 다음은 AuthSession 데이터 클래스를 이용해 세션을 DB에 저장해야 한다. but 지금은 세션처리를 안하겠다.

        // 리턴하는것은 thymeleaf로 구현된 qr코드 화면이다.
        return AuthorizationEndpointResult(
            AuthorizationViewModel(
                shortUrl,
                "https://$body_url/vc/connect/poll?pid=$presentationRequestId",
                "https://$body_url/vc/connect/callback?pid=$presentationRequestId",
                presentationRequestAsString
            )
        ).ExecuteAsync(model)
    }

    // 이 메소드는 CreatePresentationResponse 객체로 부터 필요한 정보를 파싱해 PresentationRequestMessage 객체 형태로 만들어준다.
    fun buildPresentationRequest(
        response: CreatePresentationResponse,
        acapyPublicDid: WalletPublicDid,
        aca: ACAPYClient
    ): PresentationRequestMessage {

        var request = PresentationRequestMessage()
        request.id = response.threadId!!
        request.request = PresentationRequestUtils.generatePresentationAttachments(response.presentationRequest!!)

        var service = ServiceDecorator()

        service.recipientKeys.add(acapyPublicDid.verkey)
        service.serviceEndpoint = "https://d07b4f43cbf0.ngrok.io"
        service.routingKeys = null
        request.comment = null

        request.service = service
        return request
    }
}
