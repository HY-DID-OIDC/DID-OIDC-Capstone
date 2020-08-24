package com.example.demo.Models

data class ServiceDecorator(
    var recipientKeys: MutableList<String>,
    var routingKeys: MutableList<String>,
    var serviceEndpoint: String
)
