package com.capstone.didauthoidc.acapy.models

import com.fasterxml.jackson.annotation.JsonProperty

data class WalletDidPublicResponse(
    @JsonProperty("result")
    val Result: WalletPublicDid
)

data class WalletPublicDid(
    @JsonProperty("did")
    var DID: String,

    @JsonProperty("verkey")
    var Verkey: String,

    @JsonProperty("public")
    var Public: String
)
