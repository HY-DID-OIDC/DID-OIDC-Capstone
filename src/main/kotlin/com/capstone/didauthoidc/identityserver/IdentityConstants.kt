package com.capstone.didauthoidc

class IdentityConstants{

    companion object{

        const val AuthenticationContextReferenceIdentityTokenKey : String = "acr"

        const val SubjectIdentityTokenKey : String = "sub"

        const val ClientId : String = "client_id"

        const val ScopeParamName : String = "scope"

        const val VCAuthnScopeName : String = "vc_authn"

        val MissingVCAuthnScopeError : String = "missing ${com.capstone.didauthoidc.IdentityConstants.Companion.VCAuthnScopeName} scope"

        const val PresentationRequestConfigIDParamName : String = "pres_req_conf_id"

        const val InvalidPresentationRequestConfigIDError : String = "invalid_pres_req_conf_id"

        val InvalidPresentationRequestConfigIDDesc : String = "Missing ${com.capstone.didauthoidc.IdentityConstants.Companion.PresentationRequestConfigIDParamName} param"

        const val RedirectUriParameterName : String = "redirect_uri"

        const val InvalidRedirectUriError : String = "invalid_redirect_uri"

        const val GrantTypeParameterName : String = "grant_type"

        const val InvalidGrantTypeError : String = "invalid_grant_type"

        const val UnknownPresentationRecordId : String = "unknown_presentation_record_id"

        const val PresentationUrlBuildFailed : String = "presentation_url_build_failed"

        const val SessionStartFailed : String = "session_start_failed"

        const val AcapyCallFailed : String = "acapy_call_failed"

        const val ResponseTypeUriParameterName : String = "response_type"

        const val DefaultResponseType : String = "form_post"

        const val ResponseModeUriParameterName : String = "response_mode"

        const val DefaultResponseMode : String = "authorization_code"

        const val StateParameterName : String = "state"

        const val NonceParameterName : String = "nonce"

        const val AuthorizationCodeParameterName : String = "code"

        const val InvalidAuthorizationCodeError : String = "invalid_authorization_code"

        const val SessionIdCookieName : String = "sessionid"

        const val InvalidSessionError : String = "invalid_session"

        const val GeneralError : String = "error"

        const val AuthorizationViewName : String = "/Views/Authorize/Authorize.cshtml"

        const val ChallengeIdQueryParameterName : String = "pid"

        const val ChallengePollUri : String = "vc/connect/poll"

        const val AuthorizeCallbackUri : String = "vc/connect/callback"

        const val VerifiedCredentialAuthorizeUri : String = "vc/connect/authorize"

        const val VerifiedCredentialTokenUri : String = "vc/connet/token"

        const val OIDCTokenIssuedAt : String = "iat"





    }

}

