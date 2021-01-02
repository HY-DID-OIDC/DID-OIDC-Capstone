
package com.capstone.didauthoidc.models

import com.fasterxml.jackson.annotation.JsonAutoDetect
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.annotation.JsonSerialize

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
// @JsonSerialize 어노테이션을 추가해줘야만 나중에 ObjectMapper로 직렬화하였을때 null 값을 배제시킬 수 있음.
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
data class RequestedAttribute(
    var name: String? = null,

    var names: Array<String>? = null,

    var label: String? = null,

    @JsonProperty("restrictions")
    var restrictions: MutableList<AttributeFilter>? = mutableListOf()
)
