package com.capstone.didauthoidc.identityserver.endpoints.authorizecallbackendpoint

import org.springframework.ui.Model

class AuthorizeCallbackResult {
    private lateinit var url: String

    fun executeAsync(url: String): String {
        // TODO: C#코드 context.Response.RedirectToAbsoluteUrl(url); 를 kotlin으로 변경하기.

        return "Successfully exit AuthorizeCallbackResult"
    }
}
