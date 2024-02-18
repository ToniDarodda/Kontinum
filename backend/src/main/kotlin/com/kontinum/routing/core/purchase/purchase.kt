package com.kontinum.routing.core.purchase

import com.kontinum.repository.PurchaseRepositoryImpl
import com.kontinum.service.purchase.dto.*
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.purchaseRouting(purchaseRepository: PurchaseRepositoryImpl) {
    routing {
        route("/purchase") {

            authenticate("auth-jwt") {

                post() {
                    val param = call.receive<PurchaseCreateDTO>()

                    try {
                        val createdPurchase = purchaseRepository.createPurchase(param)

                        if (createdPurchase != null) {
                            call.respond(createdPurchase)
                            return@post
                        }

                        throw Error("Error while creating purchase")
                    } catch (err: Error) {
                        call.respond(HttpStatusCode.Unauthorized, err)
                        return@post
                    }

                }

                get("/business/{id?}") {
                    val param = call.parameters["id"]!!.toInt()

                    try {
                        val retrievedPurchases = purchaseRepository.getPurchases(param)
                        call.respond(retrievedPurchases)
                        return@get
                    } catch (err: Error) {
                        call.respond(HttpStatusCode.UnprocessableEntity, err)
                        return@get
                    }

                }

                get("/{id?}") {
                    val param = call.parameters["id"]!!.toInt()

                    try {
                        val retrievedPurchase = purchaseRepository.getPurchase(param)

                        if (retrievedPurchase != null) {
                            call.respond(retrievedPurchase)
                            return@get
                        }
                        throw Error("Error while retrieving purchase")
                    } catch (err: Error) {
                        call.respond(HttpStatusCode.UnprocessableEntity, err)
                        return@get
                    }

                }

                get("/user/{id?}") {
                    val param = call.parameters["id"]!!.toInt()

                    try {
                        val retrievedPurchases = purchaseRepository.getPurchasesByUserId(param)
                        call.respond(retrievedPurchases)
                        return@get
                    } catch (err: Error) {
                        call.respond(HttpStatusCode.UnprocessableEntity, err)
                        return@get
                    }

                }

                patch("/{id?}") {
                    val param = call.parameters["id"]!!.toInt()
                    val patchPurchase = call.receive<PurchasePatchDTO>()

                    try {
                        val patchedPurchase = purchaseRepository.patchPurchase(param, patchPurchase)
                        call.respond(patchedPurchase)
                        return@patch
                    } catch (err: Error) {
                        call.respond(HttpStatusCode.UnprocessableEntity, err)
                        return@patch
                    }

                }

                delete("/{id?}") {
                    val param = call.parameters["id"]!!.toInt()
                    try {
                        purchaseRepository.deletePurchase(param)
                        call.respond("Purchase deleted successfully!")
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
