package com.capstone.didauthoidc.acapy

class ACAPYConstants {

    companion object {

        const val ApiKeyHeader: String = "x-api-key"

        const val WalletDidPublicUri: String = "/wallet/did/public"

        const val PresentProofCreateRequest: String = "/present-proof/create-request"

        const val GetPresentationRecord: String = "/present-proof/records"

        const val VerifiedPresentationState: String = "verified"

        const val PresentationsTopic: String = "present_proof"
    }
}
