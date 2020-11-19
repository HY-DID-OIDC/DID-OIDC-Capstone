package com.capstone.didauthoidc.utils

import com.capstone.didauthoidc.models.PresentationAttachment
import com.capstone.didauthoidc.models.PresentationRequest
import com.capstone.didauthoidc.models.PresentationRequestConfiguration
import com.capstone.didauthoidc.models.PresentationRequest_v_1_0
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.module.kotlin.readValue
import java.util.Base64

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
            var presentationRequest_1_0: PresentationRequest_v_1_0 = PresentationRequest_v_1_0(configuration.name, configuration.version)

            

            val requestBody = linkedMapOf<String, PresentationRequest_v_1_0>("proof_request" to presentationRequest_1_0)

            OurJacksonObjectMapper.getMapper().setSerializationInclusion(JsonInclude.Include.NON_NULL)
            val returnResult = OurJacksonObjectMapper.getMapper().writeValueAsString(requestBody)
            OurJacksonObjectMapper.getMapper().setSerializationInclusion(JsonInclude.Include.ALWAYS)

            return returnResult
        }
    }
}
