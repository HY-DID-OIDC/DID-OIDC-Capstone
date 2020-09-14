package com.capstone.didauthoidc.models

import com.fasterxml.jackson.annotation.JsonProperty

data class PresentationPredicateInfo(
    @JsonProperty("p_type")
    var PredicateType: String,

    @JsonProperty("p_value")
    var PredicateValue: String
)
