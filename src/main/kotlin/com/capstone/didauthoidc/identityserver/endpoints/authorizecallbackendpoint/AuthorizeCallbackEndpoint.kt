package com.capstone.didauthoidc.identityserver.endpoints.authorizecallbackendpoint

import com.capstone.didauthoidc.identityserver.IdentityConstants
import com.capstone.didauthoidc.models.AuthSession
import org.springframework.ui.Model
import org.springframework.util.MultiValueMap
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/vc/connect/callback")
class AuthorizeCallbackEndpoint {

    companion object {
        const val Name = "VCAuthorizeCallback"
    }

    @RequestMapping(method = arrayOf(RequestMethod.POST))
    fun processAsync(@RequestParam param: MultiValueMap<String, String>, model: Model): String {
        // 원래 코드는 이 코드이지만, 지금은 하드코딩 하겠다.
        val sessionId: String = param.getValue(IdentityConstants.ChallengeIdQueryParameterName).toString()
//        val sessionId: String = "a88d9a79-4b39-4075-9a35-d01d621dbf86"
//        println("[DEBUG] : sessionId = ${sessionId}")
        val presentationRecordId = "test-request-config"
        val presentationRequest = "{\n" +
            "    \"name\":\"Basic Proof\",\n" +
            "    \"version\":\"1.0\",\n" +
            "    \"nonce\":\"1010780604715472902183542\",\n" +
            "    \"requested_attributes\":{\n" +
            "        \"d3774d55-c815-4dca-b494-c33ff102dda2\":{\n" +
            "            \"name\":\"email\",\n" +
            "            \"restrictions\":[\n" +
            "                \n" +
            "            ]\n" +
            "        },\n" +
            "        \"7beb897a-172e-4574-b76a-10c949dbea8e\":{\n" +
            "            \"name\":\"first_name\",\n" +
            "            \"restrictions\":[\n" +
            "                \n" +
            "            ]\n" +
            "        },\n" +
            "        \"b086ae43-ca97-45fa-9ae8-9f5539546dad\":{\n" +
            "            \"name\":\"last_name\",\n" +
            "            \"restrictions\":[\n" +
            "                \n" +
            "            ]\n" +
            "        }\n" +
            "    },\n" +
            "    \"requested_predicates\":{\n" +
            "        \n" +
            "    }\n" +
            "}"
        var requestParameters: MutableMap<String, String> = LinkedHashMap()
        requestParameters["scope"] = "openid vc_authn"
        requestParameters["state"] = "EI3kI8RFbpuIqZE_MEI0xsv18NjQOS1lkbrBtj3x2CE.wOX0F5IZd74.security-admin-console"
        requestParameters["response_type"] = "code"
        requestParameters["client_id"] = "keycloak"
        requestParameters["redirect_uri"] = "http://localhost:8180/auth/realms/vc-authn/broker/vc-authn/endpoint"
        requestParameters["nonce"] = "eEJ7joxB5CC8j_LaOaw3Dg"
        requestParameters["pres_req_conf_id"] = "test-request-config"

        // 원래는 Session DB에서 가져와야 하지만, 지금은 하드코딩 하겠다.
//        var session: AuthSession? = sessionStorageService.FindByPresentationIdAsync(sessionId)
        var session: AuthSession = AuthSession(presentationRequestId = sessionId, presentationRecordId = presentationRecordId, presentationRequest = presentationRequest, requestParameters = requestParameters)

        // response_type이 code이면 url에 code를 파라미터로 붙여서 넘겨준다. state도 있다면 state도 url에 붙인다.
        if(session.requestParameters[IdentityConstants.ResponseTypeUriParameterName] == "code") {
            var url: String = "${session.requestParameters[IdentityConstants.RedirectUriParameterName]}?code=${session.id}"

            if(session.requestParameters.contains(IdentityConstants.StateParameterName))
                url += "&state=${session.requestParameters[IdentityConstants.StateParameterName]}"

            return AuthorizeCallbackResult().executeAsync(url)
        }
        return "[ERROR] : This string can't be reached"
    }
}
