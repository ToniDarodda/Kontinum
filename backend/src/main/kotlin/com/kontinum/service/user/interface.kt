package com.kontinum.service.user

import com.kontinum.model.User
import com.kontinum.service.user.dto.UserCreateDTO
import com.kontinum.service.user.dto.UserPatchDTO

interface UserInterface {
    suspend fun createUser(data: UserCreateDTO): User?
    suspend fun getUser(userId: Int): User?
    suspend fun getUsers(): List<User>
    suspend fun getUserByEmail(email: String): User?
    suspend fun patchUser(userId: Int, data: UserPatchDTO): Int
    suspend fun putUser(userId: Int, email: String): Unit
    suspend fun deleteUser(userId: Int): Unit
}