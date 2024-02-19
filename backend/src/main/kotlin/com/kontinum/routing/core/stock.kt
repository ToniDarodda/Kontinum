package com.kontinum.routing.core

import com.kontinum.repository.StockRepositoryImpl
import com.kontinum.service.stock.dto.*
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.stocksRouting(stockRepository: StockRepositoryImpl) {
    routing {
        route("/stock") {

            authenticate("auth-jwt") {

                post() {
                    val param = call.receive<StockCreateDTO>()
                    val principal = call.principal<JWTPrincipal>()
                    val businessId = principal?.payload?.getClaim("userId")?.asInt()

                    try {
                        val createdStock = businessId?.let { it1 -> stockRepository.createStock(param, it1) }

                        if (createdStock != null) {
                            call.respond(createdStock)
                            return@post
                        }
                        throw Error("Error while creating stock")
                    } catch (err: Error) {
                        call.respond(HttpStatusCode.UnprocessableEntity, err.message.toString())
                        return@post
                    }
                }

                get() {

                    val principal = call.principal<JWTPrincipal>()
                    val businessId = principal?.payload?.getClaim("userId")?.asInt()
                    try {
                        val retrievedStocks = businessId?.let { it1 -> stockRepository.getStocks(it1) }

                        if (retrievedStocks?.isNotEmpty() == true) {
                            call.respond(retrievedStocks)
                            return@get
                        }
                        throw Error("Error while retrieving stock")
                    } catch (err: Error) {
                        call.respond(HttpStatusCode.UnprocessableEntity, err.message.toString())
                        return@get
                    }

                }

                get("/{id?}") {
                    val param = call.parameters["id"]!!.toInt()

                    try {
                        val retrievedStock = stockRepository.getStock(param)

                        if (retrievedStock != null) {
                            call.respond(retrievedStock)
                            return@get
                        }
                        throw Error("Error retrieving stock")
                    } catch (err: Error) {
                        call.respond(HttpStatusCode.UnprocessableEntity, err.message.toString().plus(", ").plus("Requested id may be wrong id: $param"));
                        return@get
                    }

                }

                patch("/{id?}") {
                    val param = call.parameters["id"]!!.toInt()
                    val patchStockData = call.receive<StockPatchDTO>()

                    try {
                        val numberOfStockPatched = stockRepository.patchStock(param, patchStockData)
                        call.respond(numberOfStockPatched)
                        return@patch
                    } catch (err: Error) {
                        call.respond(HttpStatusCode.UnprocessableEntity, err.message.toString().plus(", ").plus("Requested id may be wrong id: $param"))
                        return@patch
                    }
                }

                delete("/{id?}") {
                    val param = call.parameters["id"]!!.toInt()

                    try {
                        stockRepository.deleteStock(param.toInt())
                        call.respond(HttpStatusCode.NoContent, "Stock deleted successfully!")
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