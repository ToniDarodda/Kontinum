package com.kontinum.service.user

import com.kontinum.model.User
import com.kontinum.service.user.dto.*

interface UserInterface {
    suspend fun createUser(data: UserCreateDTO, businessId: Int): User?
    suspend fun getUser(userId: Int): User?
    suspend fun getUsers(businessId: Int): List<User>
    suspend fun getUserByEmail(email: String): User?
    suspend fun patchUser(userId: Int, data: UserPatchDTO): Int
    suspend fun deleteUser(userId: Int): Unit
}