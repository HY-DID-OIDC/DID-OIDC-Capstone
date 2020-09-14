package com.capstone.didauthoidc.models

import com.capstone.didauthoidc.utils.OurJacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class AttributeFilterTest {
    @Test
    fun whenSerializeAttributeFilter_thenSuccess() {
        val attributeFilter = AttributeFilter(
            "this_is_SchemaId",
            "this_is_SchemaIssuerDid",
            "this_is_SchemaName",
            "this_is_SchemaVersion",
            "this_is_IssuerDid",
            "this_is_CredentialDefinitionId"
        )

        val serialized = OurJacksonObjectMapper.getMapper().writeValueAsString(attributeFilter).trim()

        /* Test할때는 '\n'이랑 ' '가 없게 하여 test할것. ktlint가 한 line에 120문자를 넘기면 안되게 강제하므로 우선
        이런식으로 짤라서 선언하겠음. */
        val json =
            """{"schema_id":"this_is_SchemaId",
            |"schema_issuer_did":"this_is_SchemaIssuerDid",
            |"schema_name":"this_is_SchemaName",
            |"schema_version":"this_is_SchemaVersion",
            |"issuer_did":"this_is_IssuerDid",
            |"cred_def_id":"this_is_CredentialDefinitionId"}""".trimMargin().trim()

        assertEquals(serialized, json)
    }

    @Test
    fun whenDeserializeAttributeFilter_thenSuccess() {
        val json =
            """{"schema_id":"this_is_SchemaId",
            |"schema_issuer_did":"this_is_SchemaIssuerDid",
            |"schema_name":"this_is_SchemaName",
            |"schema_version":"this_is_SchemaVersion",
            |"issuer_did":"this_is_IssuerDid",
            |"cred_def_id":"this_is_CredentialDefinitionId"}""".trimMargin().trim()

        val attributeFilter = OurJacksonObjectMapper.getMapper().readValue<AttributeFilter>(json)

        val attributeFilterAnswer = AttributeFilter(
            "this_is_SchemaId",
            "this_is_SchemaIssuerDid",
            "this_is_SchemaName",
            "this_is_SchemaVersion",
            "this_is_IssuerDid",
            "this_is_CredentialDefinitionId"
        )

        assertEquals(attributeFilter.CredentialDefinitionId, attributeFilterAnswer.CredentialDefinitionId)
        assertEquals(attributeFilter.IssuerDid, attributeFilterAnswer.IssuerDid)
        assertEquals(attributeFilter.SchemaId, attributeFilterAnswer.SchemaId)
        assertEquals(attributeFilter.SchemaIssuerDid, attributeFilterAnswer.SchemaIssuerDid)
        assertEquals(attributeFilter.SchemaName, attributeFilterAnswer.SchemaName)
        assertEquals(attributeFilter.SchemaVersion, attributeFilterAnswer.SchemaVersion)
    }
}
