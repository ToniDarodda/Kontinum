package com.kontinum.service.user.dto

import kotlinx.serialization.Serializable

@Serializable
data class UserCreateDTO(
    val firstName: String,
    val lastName: String,
    val email: String,
    val password: String
)