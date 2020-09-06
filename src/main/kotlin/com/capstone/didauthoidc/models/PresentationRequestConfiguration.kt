package com.capstone.didauthoidc.models

import com.fasterxml.jackson.annotation.JsonAutoDetect
import com.fasterxml.jackson.annotation.JsonProperty

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
data class PresentationRequestConfiguration(
    var name: String,

    var version: String,

    @JsonProperty("requested_attributes")
    var requestedAttributes: MutableList<RequestedAttribute> = mutableListOf(),

    @JsonProperty("requested_predicates")
    var requestedPredicates: MutableList<RequestedPredicate> = mutableListOf()
)
