package com.capstone.didauthoidc.models

data class ServiceDecorator(
    var recipientKeys: MutableList<String>,

    var routingKeys: MutableList<String>,

    var serviceEndpoint: String
)
