package com.capstone.didauthoidc.models

import org.springframework.stereotype.Component

@Component
data class AuthSession(

    var Id: String?,

    val ExpiredTimestamp: String?,

    var PresentationRecordId: String?,

    var PresentationRequestId: String?,

    var PresentationRequestSatisfied: Boolean?,

    var PresentationRequest: String?,

    private var _requestParameters: String?,

    var RequestParameters: Map<String, String> = LinkedHashMap(),

    private var _presentation: String?,

    var Presentation: Map<String, String> = LinkedHashMap(),
)
