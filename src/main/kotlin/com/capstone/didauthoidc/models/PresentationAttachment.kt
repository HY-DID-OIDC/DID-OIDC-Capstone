package com.capstone.didauthoidc.models

import com.fasterxml.jackson.annotation.JsonProperty

data class PresentationAttachment(
    @JsonProperty("@id")
    var id: String = "",

    @JsonProperty("mime-type")
    var mimeType: String = "",

    @JsonProperty("data")
    var data: LinkedHashMap<String, String> = LinkedHashMap()
)
