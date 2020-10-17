package com.capstone.didauthoidc.models

import com.fasterxml.jackson.annotation.JsonProperty
import java.util.UUID
import java.util.Date
import kotlin.collections.LinkedHashMap

data class AuthSession (
    @JsonProperty("Id")
    var id: String = UUID.randomUUID().toString(),

    @JsonProperty("ExpiredTimestamp")
    var expiredTimeStamp: Date? = null,

    @JsonProperty("PresentationRecordId")
    var presentationRecordId: String,

    @JsonProperty("PresentationRequestId")
    var presentationRequestId: String,

    @JsonProperty("PresentationRequestSatisfied")
    var presentationRequestSatisfied: Boolean = true,

    @JsonProperty("PresentationRequest")
    var presentationRequest: String,

    @JsonProperty("RequestParameters")
    var requestParameters: Map<String, String> = LinkedHashMap(),

    @JsonProperty("Presentation")
    var presentation: Presentation? = null
)
