package com.kontinum.routing.core


import com.kontinum.repository.BusinessRepository
import com.kontinum.service.business.dto.BusinessCreateDTO
import com.kontinum.service.business.dto.BusinessGetDTO
import com.kontinum.service.business.dto.BusinessPatchDTO
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.businessRouting(businessRepository: BusinessRepository) {
    routing {
        route("/business") {

                post("/register") {
                    val params = call.receive<BusinessCreateDTO>()
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
                    }

                    call.respond(HttpStatusCode.BadRequest,"Error in payload sent to create a business!")
                }

                post("/login") {
                    val params = call.receive<BusinessGetDTO>()
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
                    }
                    call.respond(HttpStatusCode.BadRequest,"Credentials invalid!")
                }


            authenticate("auth-jwt") {

                get("/{id}") {
                    val principal = call.principal<JWTPrincipal>()
                    val businessId = principal?.payload?.getClaim("userId")


                    val retrievedBusiness = businessId?.asInt()?.let { it1 -> businessRepository.getBusiness(it1) }
                    if (retrievedBusiness != null) call.respond(retrievedBusiness)
                    call.respond(HttpStatusCode.NotFound, "BusinessId does not exist: $businessId")
                }

                get("/users") {
                    val principal = call.principal<JWTPrincipal>()
                    val businessId = principal?.payload?.getClaim("userId")
                    val retrievedUsers = businessId?.asInt()?.let { it1 -> businessRepository.getBusinessUser(it1) }

                    if (retrievedUsers != null) {
                        call.respond(retrievedUsers)
                    }

                    call.respond(HttpStatusCode.NotAcceptable, "No users retrieved!")
                }

                patch("/{id?}") {
                    val principal = call.principal<JWTPrincipal>()
                    val businessId = principal?.payload?.getClaim("userId")
                    val param = call.receive<BusinessPatchDTO>()

                    if (businessId?.asInt() != null) {
                        val patchedUser = businessRepository.patchBusiness(businessId.asInt()!!, param)

                        call.respond(patchedUser)
                    }

                    call.respond(HttpStatusCode.NotFound, "BusinessId not found: $param")
                }

                delete("/{id?}") {

                    val principal = call.principal<JWTPrincipal>()
                    val businessId = principal?.payload?.getClaim("userId")?.asInt()

                    if (businessId == null) {
                        call.respondText("Missing BusinessId!")
                    } else {
                        businessRepository.deleteBusiness(businessId)
                        call.respondText("Business deleted successfully!")
                    }
                }

            }
        }
    }
}
