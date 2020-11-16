package com.capstone.didauthoidc.models

import com.fasterxml.jackson.annotation.JsonAnyGetter
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.JsonNode

data class ProofAttribute(
    @JsonProperty("raw") var raw: String,

    @get:JsonAnyGetter var rest: Map<String, JsonNode> = LinkedHashMap()
)
