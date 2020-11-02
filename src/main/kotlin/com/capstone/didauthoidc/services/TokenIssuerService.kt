package com.capstone.didauthoidc.services

import com.capstone.didauthoidc.services.contracts.ITokenIssuerService
import org.springframework.stereotype.Component

@Component
class TokenIssuerService : ITokenIssuerService {

    override fun IssueJwtAsync(lifetime: Int, issuer: String, audiences: List<String>, claims: List<HashMap<String, String>>) {
    }
}
