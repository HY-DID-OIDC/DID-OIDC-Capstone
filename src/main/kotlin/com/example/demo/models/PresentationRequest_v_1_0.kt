package com.example.demo.models

data class PresentationRequest_v_1_0(
    var name: String,
    var version: String,
    var nonRevoked: RevocationInterval,
    var requestedAttributes: Map<String, RequestedAttribute> = LinkedHashMap(),
    var requestedPredicates: Map<String, RequestedPredicate> = LinkedHashMap()
)
