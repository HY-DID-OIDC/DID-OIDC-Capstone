package com.capstone.didauthoidc.services.contracts

import com.capstone.didauthoidc.models.AuthSession
import com.capstone.didauthoidc.models.Presentation

interface ISessionStorageService {

    fun CreateSessionAsync(session: AuthSession)

    fun AddSession(session: AuthSession)

    fun SatisfyPresentationRequestIdAsync(presentationRequestId: String, partialPresentation: Presentation)

    fun FindByPresentationIdAsync(presentationRequestId: String)

    fun DeleteSession(session: AuthSession)
}
