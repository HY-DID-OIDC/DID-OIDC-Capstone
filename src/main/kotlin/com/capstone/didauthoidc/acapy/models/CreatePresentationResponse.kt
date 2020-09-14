package com.capstone.didauthoidc.acapy.models

import com.capstone.didauthoidc.models.PresentationRequest
import com.fasterxml.jackson.annotation.JsonProperty

data class CreatePresentationResponse(
    @JsonProperty("thread_id")
    var ThreadId: String,

    @JsonProperty("presentation_exchange_id")
    var PresentationExchangeId: String,

    @JsonProperty("presentation_request")
    var PresentationRequest: PresentationRequest
)
