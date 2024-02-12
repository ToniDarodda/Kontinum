package com.kontinum.routing.core.purchase

import com.kontinum.repository.PurchaseDetailRepository
import com.kontinum.service.purchaseDetail.dto.PurchaseDetailsCreateDTO
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.purchaseDetail(purchaseDetailRepository: PurchaseDetailRepository) {
    routing {

        route("/purchase/detail") {

            post() {
                val param = call.receive<PurchaseDetailsCreateDTO>()

                val createdPurchaseDetail = purchaseDetailRepository.createPurchaseDetail(param)

                if (createdPurchaseDetail != null) {
                    call.respond(createdPurchaseDetail)
                }

                call.respond(HttpStatusCode.BadRequest, "Data missing or invalid in the payload of [post] PurchaseDetails!")
            }

            get("/{id?}") {
                val param = call.parameters["id"]?.toInt()

                val retrievedPurchaseDetail = param?.let { it1 -> purchaseDetailRepository.getPurchaseDetail(it1) }

                if (retrievedPurchaseDetail != null) {
                    call.respond(retrievedPurchaseDetail)
                }

                call.respond(HttpStatusCode.NotFound, "No Purchases Details found whit the PurchaseDetailsId: $param")
            }

            get("/purchase/{id?}") {
                val param = call.parameters["id"]?.toInt()

                val retrievedPurchaseDetail = param?.let { it1 ->
                    purchaseDetailRepository.getPurchaseDetailByPurchaseId(
                        it1
                    )
                }

                println("test")
                println(retrievedPurchaseDetail)

                if (retrievedPurchaseDetail != null) {
                    call.respond(retrievedPurchaseDetail)
                }

                call.respond(HttpStatusCode.NotFound, "No Purchases Details found whit the PurchaseId: $param")
            }

            get("/business/{id?}") {
                val param = call.parameters["id"]?.toInt()

                val retrievedPurchaseDetails = param?.let { it1 ->
                    purchaseDetailRepository.getPurchaseDetailsByBusinessId(
                        it1
                    )
                }

                if (retrievedPurchaseDetails?.isNotEmpty() == true) {
                    call.respond(retrievedPurchaseDetails)
                }

                call.respond(HttpStatusCode.NotFound, "No Purchases Details found whit the BusinessId: $param")
            }

            delete("/{id?}") {
                val param = call.parameters["id"]?.toInt()

                if (param != null) {
                    purchaseDetailRepository.deletePurchaseDetail(param)
                    call.respondText { "Purchase detail deleted successfully!" }
                }

                call.respondText { "Error while processing to the deletion of Purchase details with id: $param" }
            }
        }
    }
}
