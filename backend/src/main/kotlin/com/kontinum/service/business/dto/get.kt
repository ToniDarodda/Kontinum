package com.kontinum.service.business.dto

import kotlinx.serialization.Serializable

@Serializable
data class BusinessGetDTO(val mail: String, val password: String)