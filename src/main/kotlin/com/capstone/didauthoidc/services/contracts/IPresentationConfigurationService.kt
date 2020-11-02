package com.capstone.didauthoidc.services.contracts

import com.capstone.didauthoidc.models.PresentationConfiguration

interface IPresentationConfigurationService {
    fun Create(record: PresentationConfiguration): Void

    fun CreateAsync(record: PresentationConfiguration)
}
