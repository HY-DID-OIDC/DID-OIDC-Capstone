package com.capstone.didauthoidc.utils

import com.capstone.didauthoidc.models.PresentationAttachment
import com.capstone.didauthoidc.models.PresentationRequest
import com.capstone.didauthoidc.models.PresentationRequestConfiguration
import com.capstone.didauthoidc.models.PresentationRequest_v_1_0
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.module.kotlin.readValue
import java.util.*

class PresentationRequestUtils {
    companion object {
        fun generatePresentationAttachments(
            presentationRequest: PresentationRequest
        ): MutableList<PresentationAttachment> {

            val base64Payload = Base64.getEncoder().encodeToString(
                OurJacksonObjectMapper.getMapper().writeValueAsString(presentationRequest).toByteArray()
            )
            var attachment = PresentationAttachment()
            attachment.id = "libindy-request-presentation-0"
            attachment.mimeType = "application/json"
            attachment.data["base64"] = base64Payload

            return mutableListOf(attachment)
        }

        fun extractIndyPresentationRequest(
            presentationAttachments: MutableList<PresentationAttachment>
        ): PresentationRequest {
            var presentationRequest: PresentationRequest? = null

            for (attachment in presentationAttachments) {
                if (attachment.id.equals("libindy-request-presentation-0")) {
                    presentationRequest = attachment.data["base64"]?.let {
                        OurJacksonObjectMapper.getMapper().readValue(
                            it
                        )
                    }
                }
            }

            return presentationRequest!!
        }

        fun generatePresentationRequest(configuration: PresentationRequestConfiguration): String {
            var presentationRequest_1_0 = PresentationRequest_v_1_0(configuration.name, configuration.version)

            for (reqAttribute in configuration.requestedAttributes) {
                var referent = if (!reqAttribute.label.isNullOrEmpty()) {reqAttribute.label} else {UUID.randomUUID().toString()}
                reqAttribute.label = null

                if (!presentationRequest_1_0.requestedAttributes.containsKey(referent)) {
                    presentationRequest_1_0.requestedAttributes.put(referent!!, reqAttribute)
                }
                else {
                    presentationRequest_1_0.requestedAttributes.put(disambiguateReferent(referent!!), reqAttribute)
                }
            }

            val requestBody = linkedMapOf("proof_request" to presentationRequest_1_0)

            val returnResult = OurJacksonObjectMapper.getMapper().setSerializationInclusion(JsonInclude.Include.NON_NULL).writeValueAsString(requestBody)

            return returnResult
        }

        private fun disambiguateReferent(referent: String): String {
            var refIdx = 1
            if (referent.split("~").size > 1) {
                val splitReferent: List<String> = referent.split("~")
                refIdx += splitReferent.get(splitReferent.size - 1).toInt()
            }
            return "$referent~$refIdx"
        }
    }
}
