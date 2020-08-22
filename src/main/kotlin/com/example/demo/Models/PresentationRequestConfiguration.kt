package com.example.demo.models

import com.example.demo.DemoApplication
import com.fasterxml.jackson.annotation.JsonAutoDetect
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.boot.runApplication

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
data class PresentationRequestConfiguration(
    var name: String,
    var version: String,
    @JsonProperty("requested_attributes")
    var requestedAttributes: MutableList<RequestedAttribute> = MutableList<RequestedAttribute>(),
    @JsonProperty("requested_predicates")
    var requestedPredicates: MutableList<RequestedPredicate> = MutableList<RequestedPredicate>()
)

// 테스트 코드(requestedAttributes와 requestedPredicates는 미구현으로 주석 처리 후 테스트)
fun main(args: Array<String>) {
    var test: PresentationRequestConfiguration = PresentationRequestConfiguration("basic", "v1.0")
    var result: String = ObjectMapper().writeValueAsString(test)

    println(result)
}
