package com.capstone.didauthoidc.models

import com.fasterxml.jackson.annotation.JsonAutoDetect
import com.fasterxml.jackson.annotation.JsonProperty

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
data class RequestedPredicate(
    var name: String,

    var label: String,

    var restrictions: MutableList<AttributeFilter>,

    @JsonProperty("p_value") var pValue: String,

    @JsonProperty("p_type") var pType: String
)
