package com.kontinum.routing.core

import com.kontinum.repository.CocktailRepositoryImpl
import com.kontinum.service.cocktail.dto.*
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

                    try {
                        val createdCocktail = businessId?.let { it1 -> cocktailRepository.createCocktail(params, it1) }

                        if (createdCocktail != null) {
                            call.respond(createdCocktail)
                            return@post
                        }
                        throw Error("Error while creating cocktail")

                    } catch (err: Error) {
                        call.respond(HttpStatusCode.UnprocessableEntity, err)
                        return@post
                    }

                }

                get() {
                    val principal = call.principal<JWTPrincipal>()
                    val businessId = principal?.payload?.getClaim("userId")?.asInt()
                    try {
                        val retrievedCocktail = businessId?.let { it1 -> cocktailRepository.getCocktails(it1) }

                        if (retrievedCocktail?.isNotEmpty() == true) {
                            call.respond(retrievedCocktail)
                            return@get
                        }
                        throw Error("Error while retrieving cocktails for a business")
                    } catch (err: Error) {
                        call.respond(HttpStatusCode.UnprocessableEntity, err)
                        return@get
                    }

                }

                get("/{id?}") {
                    val params = call.parameters["id"]!!.toInt()

                    try {
                        val retrievedCocktail = cocktailRepository.getCocktail(params)

                        if (retrievedCocktail != null) {
                            call.respond(retrievedCocktail)
                            return@get
                        }
                        throw Error("Error while retrieving cocktail")

                    } catch (err: Error) {
                        call.respond(HttpStatusCode.UnprocessableEntity, err)
                        return@get
                    }

                }

                patch("/{id?}") {
                    val param = call.parameters["id"]!!.toInt()
                    val patchCocktailObj = call.receive<CocktailPatchDTO>()

                    try {

                        val patchedCocktailNumber = cocktailRepository.patchCocktail(param, patchCocktailObj)

                        call.respond(patchedCocktailNumber)
                        return@patch
                    } catch (err: Error) {
                        call.respond(HttpStatusCode.UnprocessableEntity, err)
                        return@patch
                    }


                }

                delete("/{id?}") {
                    val param = call.parameters["id"]!!.toInt()

                    try {
                        cocktailRepository.deleteCocktail(param)
                        call.respondText("Cocktails has been deleted successfully!")
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