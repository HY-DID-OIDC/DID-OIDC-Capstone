package com.capstone.didauthoidc.identityserver.tokenendpoint

import com.capstone.didauthoidc.IdentityConstants
import com.capstone.didauthoidc.models.AuthSession
import com.capstone.didauthoidc.services.PresentationConfigurationService
import com.capstone.didauthoidc.services.SessionStorageService
import com.capstone.didauthoidc.services.TokenIssuerService
import net.minidev.json.JSONObject
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.ComponentScan
import org.springframework.util.MultiValueMap
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.time.LocalDateTime

@RestController
@ComponentScan(basePackages = arrayOf("com.capstone.didauthoidc.identityserver.models"))
class TokenEndpoint {

    var _tokenIssuerService: TokenIssuerService

    var _presentationConfigurationService: PresentationConfigurationService

    var _sessionStore: SessionStorageService

    @Autowired
    constructor(sessionStore: SessionStorageService, tokenIssuerService: TokenIssuerService, presentationConfigurationService: PresentationConfigurationService) {

        this._sessionStore = sessionStore

        this._tokenIssuerService = tokenIssuerService

        this._presentationConfigurationService = presentationConfigurationService
    }

    companion object {
        const val Name = "VCToken"
    }

    @PostMapping("/vc/connect/token")
    fun ProcessAsync(@RequestParam param: MultiValueMap<String, String>): JSONObject {
        var grantType = param.get(IdentityConstants.GrantTypeParameterName)

        if (grantType == null) {
            return error("Invalid grant type")
        }

        var sessionId = param.get(IdentityConstants.AuthorizationCodeParameterName)

        if (sessionId == null) {
            return error("Invalid authorization code")
        }

        var tempRequestParameter: HashMap<String, String> = HashMap()

        tempRequestParameter.set("client_id", "7926")

        var tempPresentation: Map<String, String> = HashMap()

        var session = AuthSession("tempId", LocalDateTime.now().toString(), "temp", "temp", true, "temp", "temp", tempRequestParameter, "temp", tempPresentation)

        try {
            return TokenEndpointResult(session, _tokenIssuerService, _presentationConfigurationService).ExcuteAsync()
        } catch (e: NoSuchElementException) {
            error("Falied to create a token response")
        }
    }
}
