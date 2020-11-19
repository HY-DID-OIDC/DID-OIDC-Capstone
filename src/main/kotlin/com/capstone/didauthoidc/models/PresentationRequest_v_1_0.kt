package com.capstone.didauthoidc.models

data class PresentationRequest_v_1_0(
    var name: String,

    var version: String,

    var nonRevoked: RevocationInterval? = null,

    var requestedAttributes: Map<String, RequestedAttribute> = LinkedHashMap(),

    var requestedPredicates: Map<String, RequestedPredicate> = LinkedHashMap()
)
