package com.capstone.didauthoidc.models

import com.fasterxml.jackson.annotation.JsonAnyGetter
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.JsonNode

data class RequestedProof(
    @JsonProperty("revealed_attrs")
    var revealedAttributes: Map<String, ProofAttribute> = LinkedHashMap(),

    @get:JsonAnyGetter
    var rest: Map<String, JsonNode> = LinkedHashMap()
)
