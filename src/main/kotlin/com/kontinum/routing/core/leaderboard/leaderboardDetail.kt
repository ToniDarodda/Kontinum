package com.kontinum.routing.core.leaderboard

import io.ktor.server.application.*
import io.ktor.server.routing.*

fun Application.leaderboardDetailRouting() {
    routing {

        post("/leaderboardDetail") {  }

        get("/leaderboardDetail") {  }

        get("/leaderboardDetail/{id?}") {  }

        patch("/leaderboardDetail/{id?}") {  }

        put("/leaderboardDetail/{id?}") {  }

        delete("/leaderboardDetail/{id?}") {  }
    }
}