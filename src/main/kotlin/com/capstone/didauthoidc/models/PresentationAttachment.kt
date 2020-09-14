package com.capstone.didauthoidc.models

import com.fasterxml.jackson.annotation.JsonProperty

data class PresentationAttachment(
    @JsonProperty("@id")
    var Id: String = "",

    @JsonProperty("mime-type")
    var MimeType: String = "",

    @JsonProperty("data")
    var Data: LinkedHashMap<String, String> = LinkedHashMap()
)
