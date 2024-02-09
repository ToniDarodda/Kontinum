package com.kontinum.routing.core

import com.kontinum.repository.UserRepositoryImpl
import com.kontinum.service.user.dto.UserCreateDTO
import com.kontinum.service.user.dto.UserPatchDTO
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.userRouting(userRepository: UserRepositoryImpl) {
    routing {
        route("/user") {

            post() {
                val params = call.receive<UserCreateDTO>()
                val createdUser = userRepository.registerUser(params)


                if (createdUser != null) {
                    call.respond(createdUser)
                }

                call.respond(HttpStatusCode.Conflict,"Email already used!")
            }

            patch("/{id?}") {
                val paramId = call.parameters["id"]?.toInt()
                val param = call.receive<UserPatchDTO>()

                if (paramId != null) {
                    val patchedUser = userRepository.patchUserById(paramId, param)

                    call.respond(patchedUser)
                }

                call.respond(HttpStatusCode.NotFound, "UserId not found!")
            }

            get() {
                val retrievedUsers = userRepository.getAllUSer()

                call.respond(retrievedUsers)
            }

            get("/{id}") {
                val params = call.parameters["id"]?.toInt()

                if (params == null) {
                    call.respondText("Missing userId")
                } else {
                    val retrievedUser = userRepository.getUserById(params)
                    if (retrievedUser != null) call.respond(retrievedUser)
                }
                call.respond(HttpStatusCode.NotFound, "UserId does not exist!")
            }

            delete("/{id?}") {
                val params = call.parameters["id"]?.toInt()

                if (params == null) {
                    call.respondText("Missing userId")
                } else {
                    userRepository.deleteUserById(params)
                    call.respondText("User deleted successfully!")
                }
            }
        }

    }
}