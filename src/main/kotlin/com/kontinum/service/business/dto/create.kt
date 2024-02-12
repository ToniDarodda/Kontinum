package com.kontinum.service.business.dto

import kotlinx.serialization.Serializable

@Serializable
data class BusinessCreateDTO(val businessName: String, val businessLocation: String, val businessLegalInformation: String, val businessPhoneNumber: String, val businessEmail: String, val password: String)