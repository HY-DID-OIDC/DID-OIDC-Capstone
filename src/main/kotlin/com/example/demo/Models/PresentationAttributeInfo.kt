package com.example.demo.Models

import com.fasterxml.jackson.annotation.JsonAutoDetect
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
data class PresentationAttributeInfo(
    var name: String,
    @JsonInclude(JsonInclude.Include.NON_NULL)
    var restrictions: MutableList<AttributeFilter>,
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("non_revoked")
    var nonRevoked: RevocationInterval
)
