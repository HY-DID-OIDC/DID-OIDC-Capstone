package com.capstone.didauthoidc.services

import com.capstone.didauthoidc.models.AuthSession
import com.capstone.didauthoidc.models.Presentation
import com.capstone.didauthoidc.services.contracts.ISessionStorageService
import org.springframework.stereotype.Repository

@Repository
class SessionStorageService : ISessionStorageService {
    override fun CreateSessionAsync(session: AuthSession) {
        TODO("Not yet implemented")
    }

    override fun AddSession(session: AuthSession) {
        TODO("Not yet implemented")
    }

    override fun SatisfyPresentationRequestIdAsync(presentationRequestId: String, partialPresentation: Presentation) {
        TODO("Not yet implemented")
    }

    override fun FindByPresentationIdAsync(presentationRequestId: String) {
        TODO("Not yet implemented")
    }

    override fun DeleteSession(session: AuthSession) {
        TODO("Not yet implemented")
    }
}
