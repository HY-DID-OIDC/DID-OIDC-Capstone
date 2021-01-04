package com.capstone.didauthoidc.controller

import com.capstone.didauthoidc.Authsession_constant
import com.capstone.didauthoidc.acapy.ACAPYConstants
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.ObjectMapper
import kotlinx.serialization.json.JsonObject
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestBody
import java.util.Date

@Controller
class WebHookController {
    @PostMapping("/{apiKey}/topic/{topic}")
    fun getTopicUpdateWithApiKey(
        @PathVariable(name = "apiKey") apiKey: String,
        @PathVariable(name = "topic") topic: String,
        @RequestBody update: String
    ): ResponseEntity<Any> {
        return processWebHook(apiKey, topic, update)
    }

    @PostMapping("/topic/{topic}")
    fun getTopicUpdate(
        @PathVariable(name = "topic") topic: String,
        @RequestBody update: String
    ): ResponseEntity<Any> {
        return processWebHook(null, topic, update)
    }

    fun processWebHook(apiKey: String?, topic: String, update: String): ResponseEntity<Any> {

        val mapper = ObjectMapper()

        val node: JsonNode = mapper.readTree(update)

        val state = node.path("state").asText()

        /* VC를 받았는지 아닌지의 상태를 계속 체크해 준다. */
        println("state is $state")

        if (state == "presentation_received") {

            Authsession_constant.vc_arrived = true

            val email = node.findPath("raw").asText()

            /* VC에서 파싱한 이메일을 세션 디비에 담아 놓는다. */
            Authsession_constant.email = email

            return ResponseEntity(HttpStatus.OK)
        }

        if (!ACAPYConstants.PresentationsTopic.equals(topic, ignoreCase = true)) {
            // topic이 present_proof가 아닌경우 Skipping webhook for topic.
            return ResponseEntity(HttpStatus.OK)
        }

        if (!update.equals(ACAPYConstants.SuccessfulPresentationUpdate)) {
            // Presentation Request not yet received
            return ResponseEntity(HttpStatus.OK)
        }

//        val proof: RequestedProof = OurJacksonObjectMapper.getMapper().readValue(RequestedProof, update.presentation?.get("requested_proof"))
//        var partialPresentation = Presentation(requestedProof = proof)

        return ResponseEntity(HttpStatus.OK)
    }
}

@JsonInclude(JsonInclude.Include.ALWAYS)
data class PresentationUpdate(
    @JsonProperty("created_at")
    var created_at: Date? = null,

    @JsonProperty("initiator")
    var initiator: String? = null,

    @JsonProperty("presentation_exchange_id")
    var presentation_exchange_id: String? = null,

    @JsonProperty("updated_at")
    var updated_at: Date? = null,

    @JsonProperty("connection_id")
    var connection_id: String? = null,

    @JsonProperty("state")
    var state: String? = null,

    @JsonProperty("thread_id")
    var thread_id: String? = null,

    @JsonProperty("presentation_request")
    var presentation_request: JsonObject? = null,

    @JsonProperty("presentation")
    var presentation: JsonObject? = null,

    @JsonProperty("verified")
    var verified: Boolean? = null
)
