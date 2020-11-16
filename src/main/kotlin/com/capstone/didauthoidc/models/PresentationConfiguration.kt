package com.capstone.didauthoidc.models

import com.fasterxml.jackson.annotation.JsonProperty

data class PresentationConfiguration(
    @JsonProperty("id")
    var id: String,

    @JsonProperty("subject_identifier")
    var subjectIdentifier: String,

    var _configuration: String?,

    @JsonProperty("configuration")
    var configuration: PresentationRequestConfiguration?
)
