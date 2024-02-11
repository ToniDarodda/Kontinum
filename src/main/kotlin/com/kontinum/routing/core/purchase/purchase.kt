package com.kontinum.routing.core.purchase

import com.kontinum.repository.PurchaseRepository
import com.kontinum.service.purchase.dto.PurchaseCreateDTO
import com.kontinum.service.purchase.dto.PurchasePatchDTO
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.purchaseRouting(purchaseRepository: PurchaseRepository) {
    routing {
        route("/purchase") {

            post() {
                val param = call.receive<PurchaseCreateDTO>()

                val createdPurchase = purchaseRepository.createPurchase(param)

                if (createdPurchase != null) {
                    call.respond(createdPurchase)
                }

                call.respond(HttpStatusCode.BadRequest, "Data missing or invalid in the payload of [post] Purchase!")
            }

            get("/business/{id?}") {
                val param = call.parameters["id"]

                val retrievedPurchases = param?.toInt()?.let { it1 -> purchaseRepository.getPurchases(it1) }

                if (retrievedPurchases?.isNotEmpty() == true) {
                    call.respond(retrievedPurchases)
                }

                call.respond(HttpStatusCode.NotFound, "No Purchases found whit the BusinessID: $param")
            }

            get("/{id?}") {
                val param = call.parameters["id"]

                val retrievedPurchase = param?.toInt()?.let { it1 -> purchaseRepository.getPurchase(it1) }

                if (retrievedPurchase != null) {
                    call.respond(retrievedPurchase)
                }

                call.respond(HttpStatusCode.NotFound, "No Purchase found with the purchaseId $param")
            }

            get("/user/{id?}") {
                val param = call.parameters["id"]

                val retrievedPurchases = param?.toInt()?.let { it1 -> purchaseRepository.getPurchasesByUserId(it1) }
                if (retrievedPurchases != null) {
                    call.respond(retrievedPurchases)
                }

                call.respond(HttpStatusCode.NotFound, "No Purchase found with the purchaseId $param")
            }

            patch("/{id?}") {
                val param = call.parameters["id"]
                val patchPurchase = call.receive<PurchasePatchDTO>()

                val patchedPurchase = param?.toInt()?.let { paramInt -> purchaseRepository.patchPurchase(paramInt, patchPurchase) }

                if (patchedPurchase != null) {
                    call.respond(patchedPurchase)
                }

                call.respond(HttpStatusCode.BadRequest, "Data missing or invalid in the payload of [patch] Purchase!")
            }

            delete("/{id?}") {
                val param = call.parameters["id"]

                param?.toInt()?.let { paramInt -> purchaseRepository.deletePurchase(paramInt) }
                call.respond("Purchase deleted successfully!")
            }
        }
    }
}