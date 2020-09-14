package com.capstone.didauthoidc.models

import com.fasterxml.jackson.annotation.JsonAutoDetect
import com.fasterxml.jackson.annotation.PropertyAccessor
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class AttributeFilterTest {
    @Test
    fun whenSerializeAttributeFilter_thenSuccess() {
        val attributeFilter = AttributeFilter("this_is_SchemaId", "this_is_SchemaIssuerDid", "this_is_SchemaName", "this_is_SchemaVersion", "this_is_IssuerDid", "this_is_CredentialDefinitionId")

        val mapper = jacksonObjectMapper()
        mapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.NONE)
        mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY)

        val serialized = mapper.writeValueAsString(attributeFilter).trim()

        val json = """{"schema_id":"this_is_SchemaId","schema_issuer_did":"this_is_SchemaIssuerDid","schema_name":"this_is_SchemaName","schema_version":"this_is_SchemaVersion","issuer_did":"this_is_IssuerDid","cred_def_id":"this_is_CredentialDefinitionId"}""".trim()

        assertEquals(serialized, json)
    }

    @Test
    fun whenDeserializeAttributeFilter_thenSuccess() {
        val json = """{"schema_id":"this_is_SchemaId","schema_issuer_did":"this_is_SchemaIssuerDid","schema_name":"this_is_SchemaName","schema_version":"this_is_SchemaVersion","issuer_did":"this_is_IssuerDid","cred_def_id":"this_is_CredentialDefinitionId"}""".trim()
        val mapper = jacksonObjectMapper()
        mapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.NONE)
        mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY)

        val attributeFilter = mapper.readValue<AttributeFilter>(json);

        val attributeFilterAnswer = AttributeFilter("this_is_SchemaId", "this_is_SchemaIssuerDid", "this_is_SchemaName", "this_is_SchemaVersion", "this_is_IssuerDid", "this_is_CredentialDefinitionId")

        assertEquals(attributeFilter.CredentialDefinitionId, attributeFilterAnswer.CredentialDefinitionId)
        assertEquals(attributeFilter.IssuerDid, attributeFilterAnswer.IssuerDid)
        assertEquals(attributeFilter.SchemaId, attributeFilterAnswer.SchemaId)
        assertEquals(attributeFilter.SchemaIssuerDid, attributeFilterAnswer.SchemaIssuerDid)
        assertEquals(attributeFilter.SchemaName, attributeFilterAnswer.SchemaName)
        assertEquals(attributeFilter.SchemaVersion, attributeFilterAnswer.SchemaVersion)
    }
}
