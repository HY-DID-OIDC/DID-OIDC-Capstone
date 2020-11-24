package com.capstone.didauthoidc.models

import com.fasterxml.jackson.annotation.JsonInclude

@JsonInclude(JsonInclude.Include.ALWAYS)
data class ServiceDecorator(
    var recipientKeys: MutableList<String> = mutableListOf(),

    var routingKeys: MutableList<String>? = null,

    var serviceEndpoint: String = ""
)
