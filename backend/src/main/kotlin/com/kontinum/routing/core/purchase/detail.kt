package com.kontinum.routing.core.purchase

import com.kontinum.repository.PurchaseDetailRepositoryImpl
import com.kontinum.service.purchaseDetail.dto.*
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.purchaseDetail(purchaseDetailRepository: PurchaseDetailRepositoryImpl) {
    routing {

        route("/purchase/detail") {

            authenticate("auth-jwt") {

                post() {
                    val param = call.receive<PurchaseDetailsCreateDTO>()

                    try {
                        val createdPurchaseDetail = purchaseDetailRepository.createPurchaseDetail(param)
                        if (createdPurchaseDetail != null) {
                            call.respond(createdPurchaseDetail)
                            return@post
                        }
                        throw Error("Error while creating detail")
                    } catch (err: Error) {
                        call.respond(HttpStatusCode.UnprocessableEntity, err)
                        return@post
                    }

                }

                get("/{id?}") {
                    val param = call.parameters["id"]!!.toInt()

                    try {
                        val retrievedPurchaseDetail = purchaseDetailRepository.getPurchaseDetail(param)

                        if (retrievedPurchaseDetail != null) {
                            call.respond(retrievedPurchaseDetail)
                            return@get
                        }
                        throw Error("Error while retrieving purchase detail")
                    } catch (err: Error) {
                        call.respond(HttpStatusCode.UnprocessableEntity, err)
                        return@get
                    }

                }

                get("/purchase/{id?}") {
                    val param = call.parameters["id"]!!.toInt()

                    try {
                        val retrievedPurchaseDetail = purchaseDetailRepository.getPurchaseDetailByPurchaseId(param)
                        call.respond(retrievedPurchaseDetail)
                        return@get
                    } catch (err: Error) {
                       call.respond(HttpStatusCode.UnprocessableEntity, err)
                        return@get
                    }

                }

                get("/business/{id?}") {
                    val param = call.parameters["id"]!!.toInt()

                    try {
                        val retrievedPurchaseDetails = purchaseDetailRepository.getPurchaseDetailsByBusinessId(param)
                        call.respond(retrievedPurchaseDetails)
                        return@get
                    } catch (err: Error) {
                        call.respond(HttpStatusCode.UnprocessableEntity, err)
                        return@get
                    }

                }

                delete("/{id?}") {
                    val param = call.parameters["id"]!!.toInt()

                    try {
                        purchaseDetailRepository.deletePurchaseDetail(param)
                        call.respondText { "Purchase detail deleted successfully!" }
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
