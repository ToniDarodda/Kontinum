package com.kontinum.routing.core

import com.kontinum.repository.LeaderboardRepositoryImpl
import com.kontinum.service.leaderboard.dto.*
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.leaderboardRouting(leaderboardRepository: LeaderboardRepositoryImpl) {
    routing {

        route("/leaderboard") {

            authenticate("auth-jwt") {

                post() {
                    val param = call.receive<LeaderboardCreateDTO>()
                    val principal = call.principal<JWTPrincipal>()
                    val businessId = principal?.payload?.getClaim("userId")?.asInt()

                    try {
                        val createdLeaderboard = businessId?.let { it1 ->
                            leaderboardRepository.createLeaderboard(param,
                                it1
                            )
                        }

                        if (createdLeaderboard != null) {
                            call.respond(createdLeaderboard)
                            return@post
                        }
                        throw Error("Error while creating leaderboard")

                    } catch (err: Error) {
                        call.respond(HttpStatusCode.UnprocessableEntity, err.message.toString())
                        return@post
                    }


                }

                get("/{leaderboardId?}") {
                    val param = call.parameters["leaderboardId"]!!.toInt()

                    try {
                        val retrievedLeaderboard = leaderboardRepository.getLeaderboard(param)

                        if (retrievedLeaderboard != null) {
                            call.respond(retrievedLeaderboard)
                            return@get
                        }
                        throw Error("Error while retrieving leaderboard")
                    } catch (err: Error) {
                        call.respond(HttpStatusCode.UnprocessableEntity, err.message.toString().plus(", ").plus("Requested id may be wrong id: $param"))
                        return@get
                    }

                }

                patch("/{leaderboardId?}") {
                    val param = call.parameters["leaderboardId"]!!.toInt()

                    val patchLeaderboard = call.receive<LeaderboardPatchDTO>()

                    try {
                        val patchedLeaderboard = leaderboardRepository.patchLeaderBoard(param, patchLeaderboard)
                        call.respond(patchedLeaderboard)
                        return@patch

                    } catch (err: Error) {
                        call.respond(HttpStatusCode.NotFound, err.message.toString().plus(", ").plus("Requested id may be wrong id: $param"))
                        return@patch
                    }
                }

                delete("/{leaderboardId?}") {
                    val param = call.parameters["leaderboardId"]!!.toInt()

                    try {
                        leaderboardRepository.deleteLeaderboard(param)
                        call.respond("Purchase deleted successfully!")
                        return@delete
                    } catch (err: Error) {
                        call.respond(HttpStatusCode.UnprocessableEntity, err.message.toString().plus(", ").plus("Requested id may be wrong id: $param"))
                        return@delete
                    }

                }

            }

            route("/detail") {

                authenticate("auth-jwt") {

                    post() {
                        val param = call.receive<LeaderboardDetailsCreateDTO>()

                        try {
                            val createdDetail = leaderboardRepository.createLeaderboardDetail(param)

                            if (createdDetail != null) {
                                call.respond(createdDetail)
                                return@post
                            }
                            throw Error("Error while creating leaderboard details")

                        } catch (err: Error) {
                            call.respond(HttpStatusCode.UnprocessableEntity, err.message.toString())
                            return@post
                        }

                    }

                    post("/many") {
                        val param = call.receive<List<LeaderboardDetailsCreateDTO>>()

                        try {
                            val createdDetails = param.map { leaderboardRepository.createLeaderboardDetail(it) }

                            if (createdDetails.isNotEmpty()) {
                                call.respond(createdDetails)
                                return@post
                            }
                            throw Error("Error while creating many leaderboard details")
                        } catch (err: Error) {
                            call.respond(HttpStatusCode.UnprocessableEntity, err.message.toString())
                            return@post
                        }


                    }

                    get("/{userId?}") {
                        val param = call.parameters["userId"]!!.toInt()

                        try {
                            val retrievedLeaderboardDetails = leaderboardRepository.getLeaderboardDetailsByUserId(param)
                            call.respond(retrievedLeaderboardDetails)
                            return@get
                        } catch (err: Error) {
                            call.respond(HttpStatusCode.UnprocessableEntity, err.message.toString().plus(", ").plus("Requested id may be wrong id: $param"))
                            return@get
                        }

                    }

                }
            }
        }
    }
}
