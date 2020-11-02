package com.capstone.didauthoidc.identityserver.tokenendpoint

import com.capstone.didauthoidc.IdentityConstants
import com.capstone.didauthoidc.models.AuthSession
import com.capstone.didauthoidc.services.PresentationConfigurationService
import com.capstone.didauthoidc.services.TokenIssuerService
import net.minidev.json.JSONObject
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Configuration
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.security.Keys
import java.util.Date

@Configuration
class TokenEndpointResult {
    var _session: AuthSession

    var _tokenIssuerService: TokenIssuerService

    var _presentationConfigurationService: PresentationConfigurationService

    @Autowired
    constructor(session: AuthSession, tokenIssuerService: TokenIssuerService, presentationConfigurationService: PresentationConfigurationService) {

        _session = session

        _tokenIssuerService = tokenIssuerService

        _presentationConfigurationService = presentationConfigurationService
    }

    fun ExcuteAsync(): JSONObject {
        println("Constructing token result")

        var audience: String? = _session!!.RequestParameters.get(IdentityConstants.ClientId)

        val now = Date()

        val keyStr = "dummyKeyStringdummyKeyStringdummyKeyStringdummyKeyStringdummyKeyStringdummyKeyString"

        val key = Keys.hmacShaKeyFor(keyStr.toByteArray())

        val header = mapOf("alg" to "HS256", "typ" to "jwt")

        var jwt = Jwts.builder()
            .claim(IdentityConstants.PresentationRequestConfigIDParamName, _session.PresentationRecordId)
            .claim(IdentityConstants.AuthenticationContextReferenceIdentityTokenKey, IdentityConstants.VCAuthnScopeName)
            .claim(IdentityConstants.OIDCTokenIssuedAt, now)
            .setIssuer("vc-authn-op")
            .setAudience(audience)
            .setExpiration(Date(now.time + 10000))
            .signWith(key)
            .setHeader(header)
            .compact()

        val token = JSONObject()

        token.put("access_key", "invalid")
        token.put("id_token", jwt)
        token.put("token_type", "Bearer")

        return token
    }
}
