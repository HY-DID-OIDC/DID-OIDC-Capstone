package com.capstone.didauthoidc.models

import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import java.util.UUID
import kotlin.collections.LinkedHashMap

@Component
data class AuthSession(

    @Value("id")
    var id: String = UUID.randomUUID().toString(),

    @Value("ExpiredTimestemp")
    val expiredTimestamp: String? = null,

    @Value("PresentationRecordId")
    var presentationRecordId: String,

    @Value("PresentationRequestId")
    var presentationRequestId: String,

    @Value("true")
    var presentationRequestSatisfied: Boolean = true,

    @Value("PresentationRequest")
    var presentationRequest: String,

    @Value("_requestParameters")
    private var _requestParameters: String? = null,

    var requestParameters: Map<String, String> = LinkedHashMap(),

    @Value("_presentation")
    private var _presentation: String? = null,

    var presentation: Map<String, String> = LinkedHashMap(),
)
