package com.capstone.didauthoidc.identityserver.endpoints.tokenendpoint

import com.capstone.didauthoidc.Authsession_constant
import com.capstone.didauthoidc.identityserver.IdentityConstants
import com.capstone.didauthoidc.models.AuthSession
import com.capstone.didauthoidc.services.PresentationConfigurationService
import com.capstone.didauthoidc.services.TokenIssuerService
import com.nimbusds.jose.JWSSigner
import com.nimbusds.jose.JOSEObjectType
import com.nimbusds.jose.Payload
import net.minidev.json.JSONObject
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Configuration
import com.nimbusds.jose.crypto.RSASSASigner
import com.nimbusds.jose.jwk.gen.RSAKeyGenerator
import com.nimbusds.jose.JWSAlgorithm
import com.nimbusds.jose.JWSHeader
import com.nimbusds.jose.JWSObject
import net.minidev.json.JSONArray
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.GetMapping

@Configuration
@RestController
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

        var audience: String? = "django-oidc-demo"

        val rsaJWK = RSAKeyGenerator(2048).keyID("E9DEA70444E24B7DE49B9118A7C696C6").generate()

        val rsaPublicJWK = rsaJWK.toPublicJWK()

        var jsonArr1 = JSONArray()

        val signer: JWSSigner = RSASSASigner(rsaJWK)

        val now = System.currentTimeMillis() / 1000

        // id_token 내부 클레임 구성 //
        val token_json = JSONObject()
        token_json.put(IdentityConstants.OIDCTokenIssuedAt, now)
        token_json.put("exp", now + 10000)
        token_json.put(IdentityConstants.PresentationRequestConfigIDParamName, "verified_email")
        token_json.put(IdentityConstants.AuthenticationContextReferenceIdentityTokenKey, IdentityConstants.VCAuthnScopeName)
        token_json.put("sub", Authsession_constant.email)
        token_json.put("email", Authsession_constant.email)
        token_json.put("nonce", Authsession_constant.nonce)
        token_json.put("iss", Authsession_constant.issued_at)
        token_json.put("aud", audience)

        // 헤더파일 만들고 sign하는 과정//
        val type = JOSEObjectType("at+jwt")

        val jwsObject = JWSObject(
            JWSHeader.Builder(JWSAlgorithm.RS256).keyID(rsaJWK.keyID).type(type).build(),
            Payload(token_json)
        )

        jwsObject.sign(signer)

        val jwt = jwsObject.serialize()

        // rsaPublic Key는 "keys": [ {key} , {key} ...] 로 구성되어있어야 하므로 포멧을 맞춰주는 과정//
        jsonArr1.appendElement(rsaPublicJWK.toJSONObject())

        // rsa public key를 디비에 담는다 //
        Authsession_constant.keys.appendField("keys", jsonArr1)

        val token = JSONObject()
        token.put("access_token", "invalid")
        token.put("id_token", jwt)
        token.put("token_type", "Bearer")

        println("return token to RP")

        return token
    }

    @GetMapping("/.well-known/openid-configuration/jwks")
    fun return_publicKey(): JSONObject {
        println("return RSA public key")
        return Authsession_constant.keys
    }
}
