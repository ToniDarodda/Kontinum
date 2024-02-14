package com.kontinum.service.user.dto

import kotlinx.serialization.Serializable

@Serializable
data class UserPatchDTO(val firstName: String?, val lastName: String?, val email: String?)