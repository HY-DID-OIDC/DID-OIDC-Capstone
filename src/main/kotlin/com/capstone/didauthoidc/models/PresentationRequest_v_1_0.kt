package com.capstone.didauthoidc.models

import com.fasterxml.jackson.annotation.JsonAutoDetect
import com.fasterxml.jackson.annotation.JsonProperty

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
data class PresentationRequest_v_1_0(
    var name: String,

    var version: String,

    var nonRevoked: RevocationInterval? = null,

    @JsonProperty("requested_attributes")
    var RequestedAttributes: MutableMap<String, RequestedAttribute> = mutableMapOf(),

    @JsonProperty("requested_predicates")
    var RequestedPredicates: MutableMap<String, RequestedPredicate> = mutableMapOf()

)
