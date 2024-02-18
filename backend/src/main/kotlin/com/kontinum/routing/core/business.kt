package com.kontinum.routing.core


import com.kontinum.repository.BusinessRepositoryImpl
import com.kontinum.service.business.dto.*
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.businessRouting(businessRepository: BusinessRepositoryImpl) {
    routing {
        route("/business") {

                post("/register") {
                    val params = call.receive<BusinessCreateDTO>()

                    try {
                        val token = businessRepository.createBusiness(params)

                        if (token != null) {
                            call.response.cookies.append(
                                name = "Authorization",
                                value = token,
                                secure = false,
                                httpOnly = true,
                                path = "/"
                            )
                            call.respondText("Business created successfully")
                            return@post
                        }
                        throw Error("Error while registering business")
                    } catch (err: Error) {
                        call.respond(HttpStatusCode.UnprocessableEntity,err)
                        return@post
                    }

                }

                post("/login") {
                    val params = call.receive<BusinessGetDTO>()

                    try {
                        val token = businessRepository.loginBusiness(params)

                        if (token != null) {
                            call.response.cookies.append(
                                name = "Authorization",
                                value = token,
                                secure = false,
                                httpOnly = true,
                                path = "/"
                            )
                            call.respondText(token)
                            return@post
                        }
                        throw Error("Error while login business")
                    } catch (err: Error) {
                        call.respond(HttpStatusCode.BadRequest,err)
                        return@post
                    }
                }


            authenticate("auth-jwt") {

                get() {
                    val principal = call.principal<JWTPrincipal>()
                    val businessId = principal?.payload?.getClaim("userId")


                    try {
                        val retrievedBusiness = businessId?.asInt()?.let { it1 -> businessRepository.getBusiness(it1) }
                        if (retrievedBusiness != null) {
                            call.respond(retrievedBusiness)
                            return@get
                        }
                        throw Error("Error while retrieving business")
                    } catch (err: Error) {
                        call.respond(HttpStatusCode.UnprocessableEntity, err)
                        return@get
                    }
                }

                get("/users") {
                    val principal = call.principal<JWTPrincipal>()
                    val businessId = principal?.payload?.getClaim("userId")

                    try {
                        val retrievedUsers = businessId?.asInt()?.let { it1 -> businessRepository.getBusinessUser(it1) }

                        if (retrievedUsers != null) {
                            call.respond(retrievedUsers)
                            return@get
                        }
                        throw Error("Error while retrieving users of a business")
                    } catch (err: Error) {
                        call.respond(HttpStatusCode.UnprocessableEntity, err)
                        return@get
                    }

                }

                patch() {
                    val principal = call.principal<JWTPrincipal>()
                    val businessId = principal?.payload?.getClaim("userId")
                    val param = call.receive<BusinessPatchDTO>()

                    try {
                        if (businessId?.asInt() != null) {
                            val patchedUser = businessRepository.patchBusiness(businessId.asInt()!!, param)

                            call.respond(patchedUser)
                            return@patch
                        }
                        throw Error("Error while patching business")
                    } catch (err: Error) {
                        call.respond(HttpStatusCode.UnprocessableEntity, err)
                        return@patch
                    }

                }

                delete() {

                    val principal = call.principal<JWTPrincipal>()
                    val businessId = principal?.payload?.getClaim("userId")?.asInt()

                    if (businessId == null) {
                        call.respondText("Missing BusinessId!")
                        return@delete
                    }

                    try {
                        businessRepository.deleteBusiness(businessId)
                        call.respondText("Business deleted successfully!")
                        return@delete
                    } catch (err: Error) {
                        call.respond(HttpStatusCode.UnprocessableEntity, err)
                        return@delete
                    }
                }

            }
        }
    }
}
