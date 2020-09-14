package com.capstone.didauthoidc.utils

import com.capstone.didauthoidc.models.PresentationAttachment
import com.capstone.didauthoidc.models.PresentationRequest
import java.util.Base64

class PresentationRequestUtils {
    companion object {
        fun generatePresentationAttachments(presentationRequest: PresentationRequest) : MutableList<PresentationAttachment> {
            val base64Payload = Base64.getEncoder().encodeToString(OurJacksonObjectMapper.getMapper().writeValueAsString(presentationRequest).toByteArray())
            var attachment = PresentationAttachment()
            attachment.Id = "libindy-request-presentation-0"
            attachment.MimeType = "application/json"
            attachment.Data["base64"] = base64Payload

            return mutableListOf(attachment)
        }
    }
}
