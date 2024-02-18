package com.kontinum.routing.core

import com.kontinum.repository.CocktailRepositoryImpl
import com.kontinum.service.cocktail.dto.CocktailCreateDTO
import com.kontinum.service.cocktail.dto.CocktailPatchDTO
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.cocktailRouting(cocktailRepository: CocktailRepositoryImpl) {
    routing {
        route("/cocktail") {
            authenticate("auth-jwt") {

                post() {
                    val principal = call.principal<JWTPrincipal>()
                    val businessId = principal?.payload?.getClaim("userId")?.asInt()
                    val params = call.receive<CocktailCreateDTO>()

                    val createdCocktail = businessId?.let { it1 -> cocktailRepository.createCocktail(params, it1) }

                    if (createdCocktail != null) call.respond(createdCocktail)

                    call.respond(HttpStatusCode.UnprocessableEntity, "Value missing in request body")
                }

                get() {
                    val principal = call.principal<JWTPrincipal>()
                    val businessId = principal?.payload?.getClaim("userId")?.asInt()
                    val retrievedCocktail = businessId?.let { it1 -> cocktailRepository.getCocktails(it1) }

                    if (retrievedCocktail?.isNotEmpty() == true) call.respond(retrievedCocktail)

                    call.respond(HttpStatusCode.NotFound, "No cocktail retrieved!")
                }

                get("/{id?}") {
                    val params = call.parameters["id"]

                    val retrievedCocktail = params?.toInt()?.let { it1 -> cocktailRepository.getCocktail(it1) }

                    if (retrievedCocktail != null) {
                        call.respond(retrievedCocktail)
                    }

                    call.respond(HttpStatusCode.NotFound, "No cocktail retrieved with id provided!")
                }

                patch("/{id?}") {
                    val param = call.parameters["id"]

                    val patchCocktailObj = call.receive<CocktailPatchDTO>()

                    val patchedCocktailNumber =
                        param?.let { it1 -> cocktailRepository.patchCocktail(it1.toInt(), patchCocktailObj) }

                    if (patchedCocktailNumber != null) {
                        call.respond(patchedCocktailNumber)
                    }

                    call.respond(HttpStatusCode.UnprocessableEntity, "No cocktail has been patched!")
                }

                delete("/{id?}") {
                    val param = call.parameters["id"]

                    if (param != null) {
                        cocktailRepository.deleteCocktail(param.toInt())
                        call.respondText("Cocktails has been deleted successfully!")
                    }

                    call.respondText("An error occurred while deleting the cocktail!")
                }

            }
        }
    }
}