package com.example.demo.Models

import java.util.Dictionary

data class PresentationRequest_v_1_0(
    var name: String,
    var version: String,
    var nonRevoked: RevocationInterval,
    var requestedAttributes: Dictionary<String, RequestedAttribute>,
    var requestedPredicates: Dictionary<String, RequestedPredicate>
)
