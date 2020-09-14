package com.capstone.didauthoidc.models

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue

data class PresentationConfiguration (
    @JsonProperty("id")
    var Id: String,
    @JsonProperty("subject_identifier")
    var SubjectIdentifier: String,
    var _configuration: String?,
    @JsonProperty("configuration")
    var Configuration: PresentationRequestConfiguration?
)
