package com.example.demo.Models

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty

data class PresentationRequest(
    var name: String,
    var version: String,
    var nonce: String,

    @JsonProperty("requested_attributes")
    var requestedAttributes: Map<String, PresentationAttributeInfo>,

    @JsonProperty("requested_predicates")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    var requestedPredicate: Map<String, PresentationPredicateInfo> = LinkedHashMap(),

    @JsonProperty("non_revoked")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    var nonRevoked: RevocationInterval
)
