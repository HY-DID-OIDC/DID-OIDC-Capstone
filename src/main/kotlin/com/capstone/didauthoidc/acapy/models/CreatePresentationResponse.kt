package com.capstone.didauthoidc.acapy.models

import com.capstone.didauthoidc.models.PresentationRequest
import com.fasterxml.jackson.annotation.JsonProperty

data class CreatePresentationResponse(
    @JsonProperty("thread_id")
    var threadId: String,

    @JsonProperty("presentation_exchange_id")
    var presentationExchangeId: String,

    @JsonProperty("presentation_request")
    var presentationRequest: PresentationRequest
)
