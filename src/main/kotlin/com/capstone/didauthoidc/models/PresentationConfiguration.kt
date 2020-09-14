package com.capstone.didauthoidc.models

import com.fasterxml.jackson.annotation.JsonProperty

data class PresentationConfiguration(
    @JsonProperty("id")
    var Id: String,
    @JsonProperty("subject_identifier")
    var SubjectIdentifier: String,
    var _configuration: String?,
    @JsonProperty("configuration")
    var Configuration: PresentationRequestConfiguration?
)
