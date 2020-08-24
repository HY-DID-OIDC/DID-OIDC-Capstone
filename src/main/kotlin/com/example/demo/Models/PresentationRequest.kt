package com.example.demo.Models

import com.example.demo.models.PresentationAttributeInfo
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import java.util.Dictionary

data class PresentationRequest(
    var name: String,
    var version: String,
    var nonce: String,

    @JsonProperty("requested_attributes")
    var requestedAttributes: Dictionary<String, PresentationAttributeInfo>,

    @JsonProperty("requested_predicates")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    var requestedPredicate: Dictionary<String, PresentationPredicateInfo>,

    @JsonProperty("non_revoked")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    var nonRevoked: RevocationInterval
)
