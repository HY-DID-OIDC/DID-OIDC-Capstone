package com.capstone.didauthoidc.acapy.models

import com.capstone.didauthoidc.models.PresentationRequest
import com.fasterxml.jackson.annotation.JsonAutoDetect
import com.fasterxml.jackson.annotation.JsonProperty

@JsonAutoDetect()
data class CreatePresentationResponse(
    @JsonProperty("thread_id")
    var threadId: String? = null,

    @JsonProperty("presentation_exchange_id")
    var presentationExchangeId: String? = null,

    @JsonProperty("presentation_request")
    var presentationRequest: PresentationRequest? = null
)
