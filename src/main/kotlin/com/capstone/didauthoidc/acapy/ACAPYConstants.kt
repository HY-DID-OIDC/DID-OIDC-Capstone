package com.capstone.didauthoidc.acapy

data class ACAPYConstants(
    val ApiKeyHeader: String = "x-api-key",

    val WalletDidPublicUri: String = "/wallet/did/public",

    val PresentProofCreateRequest: String = "/present-proof/create-request",

    val GetPresentationRecord: String = "/present-proof/records",

    val VerifiedPresentationState: String = "verified",

    val PresentationsTopic: String = "present_proof"
)
