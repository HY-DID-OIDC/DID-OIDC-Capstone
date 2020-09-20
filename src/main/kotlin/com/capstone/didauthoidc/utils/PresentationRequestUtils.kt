package com.capstone.didauthoidc.utils

import com.capstone.didauthoidc.models.PresentationAttachment
import com.capstone.didauthoidc.models.PresentationRequest
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
    }
}
