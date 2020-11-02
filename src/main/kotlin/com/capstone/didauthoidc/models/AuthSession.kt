package com.capstone.didauthoidc.models

import java.util.UUID
import java.util.Date
import kotlin.collections.LinkedHashMap
import org.springframework.stereotype.Component
import org.springframework.beans.factory.annotation.Value

@Component
data class AuthSession (
    @Value("id")
    var id: String = UUID.randomUUID().toString(),

    @Value("ExpiredTimestamp")
    var expiredTimeStamp: Date? = null,

    @Value("PresentationRecordId")
    var presentationRecordId: String,

    @Value("PresentationRequestId")
    var presentationRequestId: String,

    @Value("true")
    var presentationRequestSatisfied: Boolean = true,

    @Value("PresentationRequest")
    var presentationRequest: String,

    @Value("_requestParameters")
    private var _requestParameters: String,

    var RequestParameters: Map<String, String> = LinkedHashMap(),

    @Value("_presentation")
    var presentation: Presentation? = null
)
