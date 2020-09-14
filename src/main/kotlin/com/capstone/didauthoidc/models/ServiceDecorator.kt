package com.capstone.didauthoidc.models

data class ServiceDecorator(
    var recipientKeys: MutableList<String> = mutableListOf(),

    var routingKeys: MutableList<String>? = mutableListOf(),

    var serviceEndpoint: String = ""
)
