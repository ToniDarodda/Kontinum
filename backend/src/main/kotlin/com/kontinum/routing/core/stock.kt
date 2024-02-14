package com.kontinum.routing.core

import com.kontinum.repository.StockRepository
import com.kontinum.service.stock.dto.StockCreateDTO
import com.kontinum.service.stock.dto.StockPatchDTO
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.stocksRouting(stockRepository: StockRepository) {
    routing {
        route("/stock") {

            authenticate("auth-jwt") {

                post() {
                    val param = call.receive<StockCreateDTO>()
                    val createdStock = stockRepository.createStock(param)

                    if (createdStock != null) {
                        call.respond(createdStock)
                    }
                    call.respond(HttpStatusCode.BadRequest, "Missing value for the request [Post -> Stocks]!")
                }

                get() {
                    val retrievedStocks = stockRepository.getStocks()
                    println(retrievedStocks)
                    if (retrievedStocks.isNotEmpty()) {
                        call.respond(retrievedStocks)
                    }

                    call.respond(HttpStatusCode.NotFound, "No Stock found!")
                }

                get("/{id?}") {
                    val param = call.parameters["id"]

                    val retrievedStock = param?.let { it1 -> stockRepository.getStock(it1.toInt()) }

                    if (retrievedStock != null) {
                        call.respond(retrievedStock)
                    }

                    call.respond(HttpStatusCode.NotFound, "No Stock found with id $param")
                }

                patch("/{id?}") {
                    val param = call.parameters["id"]
                    val patchStockData = call.receive<StockPatchDTO>()

                    if (param != null) {
                        val numberOfStockPatched = stockRepository.patchStock(param.toInt(), patchStockData)
                        call.respond(numberOfStockPatched)
                    }
                }

                delete("/{id?}") {
                    val param = call.parameters["id"]

                    if (param != null) {
                        stockRepository.deleteStock(param.toInt())
                        call.respond(HttpStatusCode.NoContent, "Stock deleted successfully!")
                    }

                }
            }
        }
    }
}