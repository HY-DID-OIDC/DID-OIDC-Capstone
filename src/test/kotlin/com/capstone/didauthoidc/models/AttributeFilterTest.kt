package com.capstone.didauthoidc.models

import com.fasterxml.jackson.annotation.JsonAutoDetect
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class AttributeFilterTest {
    @Test
    fun whenSerializeAttributeFilter_thenSuccess() {
        val attributeFilter = AttributeFilter("this_is_SchemaId", "this_is_SchemaIssuerDid", "this_is_SchemaName", "this_is_SchemaVersion", "this_is_IssuerDid", "this_is_CredentialDefinitionId")

        val mapper = jacksonObjectMapper()
        mapper.setVisibilityChecker(mapper.getSerializationConfig().getDefaultVisibilityChecker()
            .withFieldVisibility(JsonAutoDetect.Visibility.ANY)
            .withGetterVisibility(JsonAutoDetect.Visibility.NONE)
            .withSetterVisibility(JsonAutoDetect.Visibility.NONE)
            .withCreatorVisibility(JsonAutoDetect.Visibility.NONE));

        val serialized = mapper.writeValueAsString(attributeFilter).trim()

        println(serialized)

        val json = """{"credentialDefinitionId":"this_is_CredentialDefinitionId","issuerDid":"this_is_IssuerDid","schemaId":"this_is_SchemaId","schemaIssuerDid":"this_is_SchemaIssuerDid","schemaName":"this_is_SchemaName","schemaVersion":"this_is_SchemaVersion"}""".trim()

        assertEquals(serialized, json)
    }

    @Test
    fun whenDeserializeAttributeFilter_thenSuccess() {
        val json = """{"credentialDefinitionId":"this_is_CredentialDefinitionId","issuerDid":"this_is_IssuerDid","schemaId":"this_is_SchemaId","schemaIssuerDid":"this_is_SchemaIssuerDid","schemaName":"this_is_SchemaName","schemaVersion":"this_is_SchemaVersion"}""".trim()
        val mapper = jacksonObjectMapper()

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
