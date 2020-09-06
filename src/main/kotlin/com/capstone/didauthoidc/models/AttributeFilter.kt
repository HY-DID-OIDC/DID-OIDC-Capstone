package com.capstone.didauthoidc.models

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty

@JsonInclude(JsonInclude.Include.NON_NULL)
data class AttributeFilter(
    @JsonProperty("schema_id")
    var SchemaId: String?,

    @JsonProperty("schema_issuer_did")
    var SchemaIssuerDid: String?,

    @JsonProperty("schema_name")
    var SchemaName: String?,

    @JsonProperty("schema_version")
    var SchemaVersion: String?,

    @JsonProperty("issuer_did")
    var IssuerDid: String?,

    @JsonProperty("cred_def_id")
    var CredentialDefinitionId: String?
)
