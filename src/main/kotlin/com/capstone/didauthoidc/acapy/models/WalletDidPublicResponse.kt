package com.capstone.didauthoidc.acapy.models

import com.fasterxml.jackson.annotation.JsonProperty

data class WalletDidPublicResponse(
    @JsonProperty("result")
    val result: WalletPublicDid
)

data class WalletPublicDid(
    @JsonProperty("did")
    var did: String,

    @JsonProperty("verkey")
    var verkey: String,

    @JsonProperty("public")
    var public: Boolean
)
