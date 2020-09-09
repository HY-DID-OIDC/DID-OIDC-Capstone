package com.capstone.didauthoidc.models

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue

data class PresentationConfiguration (
    @JsonProperty("id")
    var Id: String,
    @JsonProperty("subject_identifier")
    var SubjectIdentifier: String,
    var _configuration: String?,
    @JsonProperty("configuration")
    var Configuration: PresentationRequestConfiguration?)
{
    val mapper = jacksonObjectMapper()
    var safeConfiguration: PresentationRequestConfiguration?
        get() = if (_configuration == null) null else mapper.readValue<PresentationRequestConfiguration>(_configuration!!)
        set(value) {
            _configuration = mapper.writeValueAsString(value)
        }
}

fun main() {
    var attributeFilter = AttributeFilter("this", "is", "a", "test code", "ok?", "ok.")

    var attributeFilters = ArrayList<AttributeFilter>()
    attributeFilters.add(attributeFilter)

    var requestedAttribute = RequestedAttribute("email", "null", attributeFilters)
    var requestedAttributes = ArrayList<RequestedAttribute>()
    requestedAttributes.add(requestedAttribute)

    var requestedPredicate = RequestedPredicate("emailPredicate", "null", attributeFilters, "thisIsPVlaue", "this is p_type")
    var requestedPredicates = ArrayList<RequestedPredicate>()
    requestedPredicates.add(requestedPredicate)

    var presentationRequestConfiguration = PresentationRequestConfiguration("Basic Proof", "1.0", requestedAttributes, requestedPredicates)

    var presentationConfiguration = PresentationConfiguration("test-request-config", "email", null, presentationRequestConfiguration)

    presentationConfiguration.safeConfiguration = presentationRequestConfiguration
    println(presentationConfiguration.safeConfiguration)
    println(presentationConfiguration)
}
