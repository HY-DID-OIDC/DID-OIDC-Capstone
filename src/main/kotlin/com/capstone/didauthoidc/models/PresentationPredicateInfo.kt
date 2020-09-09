package com.capstone.didauthoidc.models

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty

class PresentationPredicateInfo {
    var name: String? = null

    @JsonInclude(JsonInclude.Include.NON_NULL)
    var restrictions: ArrayList<AttributeFilter>? = null

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("non_revoked")
    var nonRevoked: RevocationInterval? = null

    @JsonProperty("p_type")
    var predicateType: String? = null

    @JsonProperty("p_value")
    var predicateValue: String? = null

    override fun toString(): String {
        return "PresentationPredicateInfo{" +
            "predicateType='" + predicateType + '\'' +
            ", predicateValue='" + predicateValue + '\'' +
            '}'
    }
}
