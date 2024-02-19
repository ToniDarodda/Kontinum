package com.kontinum.routing.core

import com.kontinum.repository.DiscountRepositoryImpl
import com.kontinum.service.discount.dto.*
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.discountRouting(discountRepository: DiscountRepositoryImpl) {
    routing {
        route("/discount") {

            authenticate("auth-jwt") {

                post() {
                    val param = call.receive<DiscountCreateDTO>()
                    val principal = call.principal<JWTPrincipal>()
                    val businessId = principal?.payload?.getClaim("userId")?.asInt()

                    try {
                        val createdDiscount = businessId?.let { it1 -> discountRepository.createDiscount(param, it1) }

                        if (createdDiscount != null) {
                            call.respond(createdDiscount)
                            return@post
                        }
                        throw Error("Error while creating discount")
                    } catch (err: Error) {
                        call.respond(HttpStatusCode.UnprocessableEntity, err.message.toString())
                        return@post
                    }

                }

                get() {
                    val principal = call.principal<JWTPrincipal>()
                    val businessId = principal?.payload?.getClaim("userId")?.asInt()

                    try {
                        val retrievedDiscount = businessId?.let { it1 -> discountRepository.getDiscount(it1) }

                        if (retrievedDiscount != null) {
                            call.respond(retrievedDiscount)
                            return@get
                        }
                        throw Error("Error while retrieving discount")

                    } catch (err: Error) {
                        call.respond(HttpStatusCode.UnprocessableEntity, err.message.toString())
                        return@get
                    }

                }

                patch("/{id?}") {
                    val param = call.parameters["id"]!!.toInt()
                    val patchDiscount = call.receive<DiscountPatchDTO>()

                    try {
                        val patchedDiscount = discountRepository.patchDiscount(param, patchDiscount)
                        call.respond(patchedDiscount)
                        return@patch
                    } catch (err: Error) {
                        call.respond(HttpStatusCode.UnprocessableEntity, err.message.toString().plus(", ").plus("Requested id may be wrong id: $param"))
                        return@patch
                    }
                }

                delete("/{id?}") {
                    val param = call.parameters["id"]!!.toInt()

                    try {
                        discountRepository.deleteDiscount(param)
                        call.respond("Discount with deleted successfully!")
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
