package com.kontinum.routing.core

import com.kontinum.repository.DiscountRepository
import com.kontinum.service.discount.dto.DiscountCreateDTO
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.discountRouting(discountRepository: DiscountRepository) {
    routing {
        route("/discount") {



            post() {
                val param = call.receive<DiscountCreateDTO>()

                val createdDiscount = discountRepository.createDiscount(param)

                if (createdDiscount != null) {
                    call.respond(createdDiscount)
                }

                call.respond(HttpStatusCode.BadRequest, "Data missing in [Post] Discount!")
            }

            get("/{id?}") {  }

            patch("/{id?}") {  }

            delete("/{id?}") {  }
        }
    }
}
