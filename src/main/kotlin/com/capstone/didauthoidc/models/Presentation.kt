package com.capstone.didauthoidc.models

import com.fasterxml.jackson.annotation.JsonProperty

data class Presentation(
    @JsonProperty("requested_proof")
    var requestedProof: RequestedProof
)
