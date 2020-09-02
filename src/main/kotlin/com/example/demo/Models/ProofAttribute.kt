package com.example.demo.models

import com.fasterxml.jackson.annotation.JsonAnyGetter
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.JsonNode

data class ProofAttribute(
    @JsonProperty("raw") var Raw: String,

    @get:JsonAnyGetter var Rest: Map<String, JsonNode> = LinkedHashMap()
)
