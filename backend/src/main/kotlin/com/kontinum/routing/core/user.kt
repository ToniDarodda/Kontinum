package com.kontinum.routing.core

import com.kontinum.repository.UserRepositoryImpl
import com.kontinum.service.user.dto.*
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*

fun Application.userRouting(userRepository: UserRepositoryImpl) {

    routing {

        route("/user") {

            authenticate("auth-jwt") {

                post() {
                    val param = call.receive<UserCreateDTO>()
                    val principal = call.principal<JWTPrincipal>()
                    val businessId = principal?.payload?.getClaim("userId")?.asInt()

                    try {
                        val createdUser = businessId?.let { it1 -> userRepository.registerUser(param, it1) }

                        if (createdUser != null) {
                            call.respond(createdUser)
                            return@post
                        }
                        throw Error("Error while creating user")
                    } catch (err: Error) {
                        call.respond(HttpStatusCode.UnprocessableEntity, err.message.toString())
                        return@post
                    }

                }

                patch("/{id?}") {
                    val paramId = call.parameters["id"]!!.toInt()
                    val param = call.receive<UserPatchDTO>()

                    try {
                        val patchedUser = userRepository.patchUserById(paramId, param)
                        call.respond(patchedUser)
                        return@patch
                    } catch (err: Error) {
                        call.respond(HttpStatusCode.UnprocessableEntity, err.message.toString().plus(", ").plus("Requested id may be wrong id: $param"))
                        return@patch
                    }

                }

                get() {
                    val principal = call.principal<JWTPrincipal>()
                    val businessId = principal?.payload?.getClaim("userId")?.asInt()

                    try {
                        val retrievedUsers = businessId?.let { it1 -> userRepository.getAllUSer(it1) }

                        if (retrievedUsers != null) {
                            call.respond(retrievedUsers)
                            return@get
                        }
                        throw Error("Error while retrieving business user")

                    } catch (err: Error) {
                        call.respond(HttpStatusCode.UnprocessableEntity, err.message.toString())
                        return@get
                    }
                }

                get("/{id}") {
                    val param = call.parameters["id"]!!.toInt()

                    try {
                        val retrievedUser = userRepository.getUserById(param)
                        if (retrievedUser != null) {
                            call.respond(retrievedUser)
                            return@get
                        }
                        throw Error("Error while retrieving users")
                    } catch (err: Error) {
                        call.respond(HttpStatusCode.UnprocessableEntity, err.message.toString().plus(", ").plus("Requested id may be wrong id: $param"))
                        return@get
                    }

                }

                delete("/{id?}") {
                    val param = call.parameters["id"]!!.toInt()

                    try {
                        userRepository.deleteUserById(param)
                        call.respondText("User deleted successfully!")
                        return@delete
                    } catch (err: Error) {
                        call.respond(HttpStatusCode.UnprocessableEntity, err.message.toString().plus(", ").plus("Requested id may be wrong id: $param"))
                        return@delete
                    }
                }

            }
        }
    }
}