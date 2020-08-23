package com.example.demo.Models

import com.fasterxml.jackson.annotation.JsonAnyGetter
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.core.JsonToken

data class RequestedProof(
    @JsonProperty("revealed_attrs") var RevealedAttributes : Map<String, ProofAttribute> = LinkedHashMap(),

    @get:JsonAnyGetter var Rest:Map<String, JsonToken> =LinkedHashMap()
)
