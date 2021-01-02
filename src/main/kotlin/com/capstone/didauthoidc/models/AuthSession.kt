package com.capstone.didauthoidc.models

import org.springframework.stereotype.Component
import java.util.UUID
import kotlin.collections.LinkedHashMap

@Component
data class AuthSession(

    var id: String = UUID.randomUUID().toString(),

    val expiredTimestamp: String? = null,

    var presentationRecordId: String?,

    var presentationRequestId: String?,

    var presentationRequestSatisfied: Boolean = true,

    var presentationRequest: String?,

    private var _requestParameters: String? = null,

    var requestParameters: Map<String, String> = LinkedHashMap(),

    private var _presentation: String? = null,

    var presentation: Map<String, String> = LinkedHashMap(),
)
