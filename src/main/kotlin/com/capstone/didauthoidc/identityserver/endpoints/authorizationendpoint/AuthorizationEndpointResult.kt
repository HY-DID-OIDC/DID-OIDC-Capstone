package com.capstone.didauthoidc.identityserver.endpoints.authorizationendpoint

import com.capstone.didauthoidc.IdentityConstants
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.stereotype.Controller
import org.springframework.ui.Model

@Configuration
class AuthorizationViewModel {

    var Challenge: String = ""

    var PollUrl: String = ""

    var ResolutionUrl: String = ""

    var Interval: Int = 0

    var PresentationRequest: String? = ""

    constructor(
        @Value("challenge") challenge: String,
        @Value("pollUrl") pollUrl: String,
        @Value("resolutionUrl") resolutionUrl: String,
        @Value("presentationReqeust") presentationRequest: String
    ) {
        this.Challenge = challenge

        this.PollUrl = pollUrl

        this.ResolutionUrl = resolutionUrl

        this.Interval = 2000

        this.PresentationRequest = presentationRequest
    }
}

@Controller
@ComponentScan(basePackages = arrayOf("com.capstone.didauthoidc.identityserver.endpoints"))
class AuthorizationEndpointResult {

    var _authorizationRequest: AuthorizationViewModel

    var _viewName: String = IdentityConstants.AuthorizationViewName

    constructor(authorizationRequest: AuthorizationViewModel) {
        this._authorizationRequest = authorizationRequest
    }

    fun ExecuteAsync(model: Model): String {

        model.addAttribute("presreq", _authorizationRequest.PresentationRequest)
        model.addAttribute("Challenge", _authorizationRequest.Challenge)
        model.addAttribute("Interval", _authorizationRequest.Interval)
        model.addAttribute("PollUrl", _authorizationRequest.PollUrl)
        model.addAttribute("ResolutionUrl", _authorizationRequest.ResolutionUrl)

        return "Authorize"
    }
}
