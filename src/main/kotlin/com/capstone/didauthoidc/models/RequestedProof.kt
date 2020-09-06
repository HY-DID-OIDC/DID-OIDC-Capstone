package com.example.demo.models

import com.fasterxml.jackson.annotation.JsonAnyGetter
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.JsonNode

data class RequestedProof(
    @JsonProperty("revealed_attrs")
    var RevealedAttributes: Map<String, ProofAttribute> = LinkedHashMap(),

    @get:JsonAnyGetter
    var Rest: Map<String, JsonNode> = LinkedHashMap()
)
