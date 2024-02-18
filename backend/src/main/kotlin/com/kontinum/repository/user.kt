package com.kontinum.repository

import com.kontinum.model.User
import com.kontinum.service.user.dto.UserCreateDTO
import com.kontinum.service.user.UserService
import com.kontinum.service.user.dto.UserPatchDTO

class UserRepositoryImpl(private val userService: UserService) {
    suspend fun registerUser(data: UserCreateDTO, businessId: Int): User? {
        return userService.createUser(data, businessId)
    }

    suspend fun loginUser() {}

    suspend fun getAllUSer(businessId: Int): List<User> {
        return userService.getUsers(businessId)
    }

    suspend fun getUserById(userId: Int): User? {
        return userService.getUser(userId)
    }

    suspend fun patchUserById(userId: Int, data: UserPatchDTO): Int {
        return userService.patchUser(userId, data)
    }

    suspend fun putEmailUserById() {}

    suspend fun deleteUserById(userId: Int) {
        userService.deleteUser(userId)
    }
}