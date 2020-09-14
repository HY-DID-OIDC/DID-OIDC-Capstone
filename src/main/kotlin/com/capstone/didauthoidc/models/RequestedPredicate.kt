package com.capstone.didauthoidc.models

import com.fasterxml.jackson.annotation.JsonAutoDetect
import com.fasterxml.jackson.annotation.JsonProperty

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
data class RequestedPredicate(
    var Name: String,

    var label: String,

    var Restrictions: MutableList<AttributeFilter>,

    @JsonProperty("p_value") var PValue: String,

    @JsonProperty("p_type") var PType: String
)
