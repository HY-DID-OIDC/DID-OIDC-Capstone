package com.capstone.didauthoidc.models

import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component

@Component
data class AuthSession(

    @Value("id")
    var Id: String,

    @Value("ExpiredTimestemp")
    val ExpiredTimestamp: String,

    @Value("PresentationRecordId")
    var PresentationRecordId: String,

    @Value("PresentationRequestId")
    var PresentationRequestId: String,

    @Value("true")
    var PresentationRequestSatisfied: Boolean,

    @Value("PresentationRequest")
    var PresentationRequest: String,

    @Value("_requestParameters")
    private var _requestParameters: String,

    var RequestParameters: Map<String, String> = LinkedHashMap(),

    @Value("_presentation")
    private var _presentation: String,

    var Presentation: Map<String, String> = LinkedHashMap(),
)
