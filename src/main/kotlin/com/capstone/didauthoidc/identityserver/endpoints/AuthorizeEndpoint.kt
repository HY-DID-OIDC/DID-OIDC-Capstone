package com.capstone.didauthoidc.identityserver.endpoints

import com.capstone.didauthoidc.IdentityConstants
import org.springframework.util.MultiValueMap
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RequestParam
import com.capstone.didauthoidc.acapy.ACAPYClient
import com.capstone.didauthoidc.acapy.models.WalletPublicDid
import org.springframework.stereotype.Controller
import org.springframework.ui.Model

@Controller
@RequestMapping("/vc/connect/authorize")
class AuthorizeEndpoint {

    companion object {
        const val Name = "VCAuthorize"
    }

    @RequestMapping(method = arrayOf(RequestMethod.POST, RequestMethod.GET))
    fun ProcessAsync(@RequestParam param: MultiValueMap<String, String>, model: Model): String {
        val scopes: List<String> = param.getValue("scope")[0].toString().split(" ")

        var check = 0

        for (i in scopes) {
            if (i == "vc_authn")
                check = 1
        }
        if (check == 0) {
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

        var acapyPublicDid: WalletPublicDid = aca.WalletDidPublic()

        var presentationrequestString =
            "{\"@id\":\"84331237-8af1-443a-bb1f-53df1ebac307\"," +
                "\"@type\":\"did:sov:BzCbsNYhMrjHiqZDTUASHg;spec/present-proof/1" +
                ".0/request-presentation\",\"request_presentations~attach\"" +
                ":[{\"@id\":\"libindy-request" +
                "-presentation-0\",\"mime-type\":\"application/json\"," +
                "\"data\":{\"base64\":\"eyJuYW1lIjoiQmFzaWMgUHJvb2YiLCJ2ZXJza" +
                "W9uIjoiMS4wIiwibm9uY2UiOiI3OTkwODE0ODk3MjI1OTU" +
                "3Mzg2Mjk3NDkiLCJyZXF1ZXN0ZWRfYXR0cmlidXRlcyI6e" +
                "yJjZTc4ODc0ZS03NTkxLTRhNWMtYjYzZS05YjVkNW" +
                "Y3MDM5Y2UiOnsibmFtZSI6ImVtYWlsIiwicmVzdHJpY3Rpb" +
                "25zIjpbXX0sImU1YWM3ZWQ5LTk1MzEtNGM4OC04O" +
                "WViLTlhMmYwYmIxZDgyYyI6eyJuYW1lIjoiZmlyc3RfbmF" +
                "tZSIsInJlc3RyaWN0aW9ucyI6W119LCI5ODE4YTc0" +
                "NC04NTBiLTRjNDItYmVmZC1iYjlmZTlmOGFlNGMiOnsibmF" +
                "tZSI6Imxhc3RfbmFtZSIsInJlc3RyaWN0aW9ucyI6" +
                "W119fSwicmVxdWVzdGVkX3ByZWRpY2F0ZXMiOnt9fQ==\"}}],\"comment\":null," +
                "\"~service\":{\"recipientKeys" +
                "\":[\"FYmoFw55GeQH7SRFa37dkx1d2dZ3zUF8ckg7wmL7ofN4\"]," +
                "\"routingKeys\":null," +
                "\"serviceEndpoint\":\"http://192.168.65.3:5679\"}}"

        return AuthorizationEndpointResult(
            AuthorizationViewModel(
                "http://localhost:5000/url/b801f0c2-5453-4a54-8c0b-37aba23f6a65",
                "http://localhost:5000/vc/connect/poll?pid=6270d901-0175-4663-aa5a-5142a809a4eb",
                "http://localhost:5000/vc/connect/callback?pid=6270d901-0175-4663-aa5a-5142a809a4eb",
                presentationrequestString
            )
        ).ExecuteAsnc(model)
    }
}
