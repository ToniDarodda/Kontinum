package com.kontinum.routing.core


import com.kontinum.repository.BusinessRepository
import com.kontinum.service.business.dto.BusinessCreateDTO
import com.kontinum.service.business.dto.BusinessGetDTO
import com.kontinum.service.business.dto.BusinessPatchDTO
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
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
                    val params = call.parameters["id"]?.toInt()

                    if (params == null) {
                        call.respondText("Missing businessId!")
                    } else {
                        val retrievedBusiness = businessRepository.getBusiness(params)
                        if (retrievedBusiness != null) call.respond(retrievedBusiness)
                    }
                    call.respond(HttpStatusCode.NotFound, "BusinessId does not exist: $params")
                }

                patch("/{id?}") {
                    val paramId = call.parameters["id"]?.toInt()
                    val param = call.receive<BusinessPatchDTO>()

                    if (paramId != null) {
                        val patchedUser = businessRepository.patchBusiness(paramId, param)

                        call.respond(patchedUser)
                    }

                    call.respond(HttpStatusCode.NotFound, "BusinessId not found: $param")
                }

                delete("/{id?}") {

                    val params = call.parameters["id"]?.toInt()

                    if (params == null) {
                        call.respondText("Missing BusinessId!")
                    } else {
                        businessRepository.deleteBusiness(params)
                        call.respondText("Business deleted successfully!")
                    }
                }

            }
        }
    }
}
