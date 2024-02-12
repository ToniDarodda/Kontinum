package com.kontinum.routing.core


import com.kontinum.repository.BusinessRepository
import com.kontinum.service.business.dto.BusinessCreateDTO
import com.kontinum.service.business.dto.BusinessGetDTO
import com.kontinum.service.business.dto.BusinessPatchDTO
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.businessRouting(businessRepository: BusinessRepository) {
    routing {
        route("/business") {

            post("/register") {
                val params = call.receive<BusinessCreateDTO>()
                val createdBusiness = businessRepository.createBusiness(params)


                if (createdBusiness != null) {
                    call.respond(createdBusiness)
                }

                call.respond(HttpStatusCode.BadRequest,"Error in payload sent to create a business!")
            }

            post("/login") {
                val params = call.receive<BusinessGetDTO>()
                println("ici1")
                val createdBusiness = businessRepository.loginBusiness(params)


                if (createdBusiness != null) {
                    call.respond(createdBusiness)
                }
//                if (createdBusiness != null) {
//                    call.respond(createdBusiness)
//                }
//
                call.respond(HttpStatusCode.BadRequest,"Error in payload sent to create a business!")
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