package com.capstone.didauthoidc.models

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonPropertyOrder

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder("SchemaId", "SchemaIssuerDid", "SchemaName", "SchemaVersion", "IssuerDid", "CredentialDefinitionId")
data class AttributeFilter(
    @JsonProperty("schema_id")
    var schemaId: String?,

    @JsonProperty("schema_issuer_did")
    var schemaIssuerDid: String?,

    @JsonProperty("schema_name")
    var schemaName: String?,

    @JsonProperty("schema_version")
    var schemaVersion: String?,

    @JsonProperty("issuer_did")
    var issuerDid: String?,

    @JsonProperty("cred_def_id")
    var credentialDefinitionId: String?
)
