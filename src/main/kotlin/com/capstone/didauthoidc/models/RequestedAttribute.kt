package com.capstone.didauthoidc.models

import com.fasterxml.jackson.annotation.JsonAutoDetect
import com.fasterxml.jackson.annotation.JsonProperty

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
data class RequestedAttribute(
    var name: String?,

    var label: String?,

    @JsonProperty("restrictions")
    var Restrictions: List<AttributeFilter>?
)
