package com.capstone.didauthoidc.identityserver.endpoints.authorizationendpoint

import com.capstone.didauthoidc.identityserver.IdentityConstants
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.stereotype.Controller
import org.springframework.ui.Model

@Configuration
class AuthorizationViewModel {

    var challenge: String = ""

    var pollUrl: String = ""

    var resolutionUrl: String = ""

    var interval: Int = 0

    var presentationRequest: String? = ""

    constructor(
        @Value("challenge") challenge: String,
        @Value("pollUrl") pollUrl: String,
        @Value("resolutionUrl") resolutionUrl: String,
        @Value("presentationReqeust") presentationRequest: String
    ) {
        this.challenge = challenge

        this.pollUrl = pollUrl

        this.resolutionUrl = resolutionUrl

        this.interval = 2000

        this.presentationRequest = presentationRequest
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

        model.addAttribute("presreq", _authorizationRequest.presentationRequest)
        model.addAttribute("Challenge", _authorizationRequest.challenge)
        model.addAttribute("Interval", _authorizationRequest.interval)
        model.addAttribute("PollUrl", _authorizationRequest.pollUrl)
        model.addAttribute("ResolutionUrl", _authorizationRequest.resolutionUrl)

        return "Authorize"
    }
}
