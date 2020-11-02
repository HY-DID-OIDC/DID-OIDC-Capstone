package com.capstone.didauthoidc.services.contracts

interface ITokenIssuerService {
    fun IssueJwtAsync(lifetime: Int, issuer: String, audiences: List<String>, claims: List<HashMap<String, String>>)
}
