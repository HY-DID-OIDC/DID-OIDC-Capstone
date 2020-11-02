package com.capstone.didauthoidc.models

import com.fasterxml.jackson.annotation.JsonAutoDetect

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
data class RevocationInterval(
    var From: Long,

    var To: Long
)
