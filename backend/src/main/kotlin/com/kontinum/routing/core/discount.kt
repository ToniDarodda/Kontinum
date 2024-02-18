package com.kontinum.routing.core

import com.kontinum.repository.DiscountRepository
import com.kontinum.service.discount.dto.DiscountCreateDTO
import com.kontinum.service.discount.dto.DiscountPatchDTO
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.discountRouting(discountRepository: DiscountRepository) {
    routing {
        route("/discount") {

            authenticate("auth-jwt") {

                post() {
                    val param = call.receive<DiscountCreateDTO>()
                    val principal = call.principal<JWTPrincipal>()
                    val businessId = principal?.payload?.getClaim("userId")?.asInt()

                    val createdDiscount = businessId?.let { it1 -> discountRepository.createDiscount(param, it1) }

                    if (createdDiscount != null) {
                        call.respond(createdDiscount)
                    }

                    call.respond(HttpStatusCode.BadRequest, "Data missing in [Post] Discount!")
                }

                get() {
                    val principal = call.principal<JWTPrincipal>()
                    val businessId = principal?.payload?.getClaim("userId")?.asInt()

                    val retrievedDiscount = businessId?.let { it1 -> discountRepository.getDiscount(it1) }

                    if (retrievedDiscount != null) {
                        call.respond(retrievedDiscount)
                    }

                    call.respond(HttpStatusCode.NotFound, "No discount found with business Id: $businessId")
                }

                patch("/{id?}") {
                    val param = call.parameters["id"]?.toInt()

                    val patchDiscount = call.receive<DiscountPatchDTO>()

                    val patchedDiscount = param?.let { it1 -> discountRepository.patchDiscount(it1, patchDiscount) }

                    if (patchedDiscount != null) {
                        call.respond(patchedDiscount)
                    }

                    call.respond(HttpStatusCode.NotAcceptable, "Problem happen when patching discount with id: $param")
                }

                delete("/{id?}") {
                    val param = call.parameters["id"]?.toInt()

                    if (param != null) {
                        discountRepository.deleteDiscount(param)
                        call.respond("Discount with deleted successfully!")
                    }

                    call.respond("Error occurred when deleting discount!")
                }

            }
        }
    }
}
