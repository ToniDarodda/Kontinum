package com.kontinum.routing.core

import com.kontinum.repository.LeaderboardRepository
import com.kontinum.service.leaderboard.dto.LeaderboardCreateDTO
import com.kontinum.service.leaderboard.dto.LeaderboardDetailsCreateDTO
import com.kontinum.service.leaderboard.dto.LeaderboardPatchDTO
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.leaderboardRouting(leaderboardRepository: LeaderboardRepository) {
    routing {

        route("/leaderboard") {

            authenticate("auth-jwt") {

                post() {
                    val param = call.receive<LeaderboardCreateDTO>()
                    val principal = call.principal<JWTPrincipal>()
                    val businessId = principal?.payload?.getClaim("userId")?.asInt()

                    val createdLeaderboard = businessId?.let { it1 ->
                        leaderboardRepository.createLeaderboard(param,
                            it1
                        )
                    }

                    if (createdLeaderboard != null) {
                        call.respond(createdLeaderboard)
                    }

                    call.respond(HttpStatusCode.BadRequest, "Data missing or invalid in the payload of [post] Leaderboard!")
                }

                get("/{leaderboardId?}") {
                    val param = call.parameters["leaderboardId"]?.toInt()

                    val retrievedLeaderboard = param?.let { it1 -> leaderboardRepository.getLeaderboard(it1) }

                    if (retrievedLeaderboard != null) {
                        call.respond(retrievedLeaderboard)
                    }

                    call.respond(HttpStatusCode.NotFound, "No Leader board found with leaderboardId: $param")
                }

                patch("/{leaderboardId?}") {
                    val param = call.parameters["leaderboardId"]?.toInt()

                    val patchLeaderboard = call.receive<LeaderboardPatchDTO>()

                    val patchedLeaderboard =
                        param?.let { it1 -> leaderboardRepository.patchLeaderBoard(it1, patchLeaderboard) }

                    if (patchedLeaderboard != null) {
                        call.respond(patchedLeaderboard)
                    }

                    call.respond(HttpStatusCode.NotFound, "No Leader board found with leaderboardId: $param")
                }

                delete("/{leaderboardId?}") {
                    val param = call.parameters["leaderboardId"]?.toInt()

                    if (param != null) {
                        leaderboardRepository.deleteLeaderboard(param)
                        call.respond("Purchase deleted successfully!")
                    }

                    call.respond(HttpStatusCode.UnprocessableEntity, "Error occur while deleting leaderboard!")
                }

            }

            route("/detail") {

                authenticate("auth-jwt") {

                    post() {
                        val param = call.receive<LeaderboardDetailsCreateDTO>()

                        val createdDetail = leaderboardRepository.createLeaderboardDetail(param)

                        if (createdDetail != null) {
                            call.respond(createdDetail)
                        }

                        call.respond(HttpStatusCode.BadRequest, "Data missing or invalid in the payload of [post] Leaderboard details!")
                    }

                    post("/many") {
                        val param = call.receive<List<LeaderboardDetailsCreateDTO>>()

                        val createdDetails = param.map { leaderboardRepository.createLeaderboardDetail(it) }

                        if (createdDetails.isNotEmpty()) {
                            call.respond(createdDetails)
                        }

                        call.respond(HttpStatusCode.BadRequest, "Data missing or invalid in the payload of [post] Leaderboard details many!")
                    }

                    get("/{userId?}") {
                        val param = call.parameters["userId"]?.toInt()

                        val retrievedLeaderboardDetails = param?.let { it1 ->
                            leaderboardRepository.getLeaderboardDetailsByUserId(
                                it1
                            )
                        }

                        if (retrievedLeaderboardDetails != null) {
                            call.respond(retrievedLeaderboardDetails)
                        }

                        call.respond(HttpStatusCode.NotFound, "No Leaderboard details found with userId: $param")
                    }

                }
            }
        }
    }
}
