package com.capstone.didauthoidc.models

import com.fasterxml.jackson.annotation.JsonProperty

data class PresentationRequestMessage(
    @JsonProperty("@id")
    var id: String = "",

    @JsonProperty("@type")
    var type: String = "did:sov:BzCbsNYhMrjHiqZDTUASHg;spec/present-proof/1.0/request-presentation",

    @JsonProperty("request_presentations~attach")
    var request: MutableList<PresentationAttachment> = mutableListOf(),

    var comment: String? = null,

    @JsonProperty("~service")
    var service: ServiceDecorator = ServiceDecorator()
)
