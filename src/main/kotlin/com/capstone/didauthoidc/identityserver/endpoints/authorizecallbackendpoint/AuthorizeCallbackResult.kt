package com.capstone.didauthoidc.identityserver.endpoints.authorizecallbackendpoint

class AuthorizeCallbackResult {
    private lateinit var url: String

    fun executeAsync(url: String): String {
        // TODO: C#코드 context.Response.RedirectToAbsoluteUrl(url); 를 kotlin으로 변경하기.

        return "redirect:http://localhost:8180/auth/realms/vc-authn/broker/vc-authn/endpoint"
    }
}
