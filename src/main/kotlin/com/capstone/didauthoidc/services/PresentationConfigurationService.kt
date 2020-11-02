package com.capstone.didauthoidc.services

import com.capstone.didauthoidc.models.PresentationConfiguration
import com.capstone.didauthoidc.services.contracts.IPresentationConfigurationService
import org.springframework.stereotype.Component

@Component
class PresentationConfigurationService : IPresentationConfigurationService {
    override fun Create(record: PresentationConfiguration): Void {
        TODO("Not yet implemented")
    }

    override fun CreateAsync(record: PresentationConfiguration) {
        TODO("Not yet implemented")
    }
}
