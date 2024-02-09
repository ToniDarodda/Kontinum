package com.kontinum.routing.core.leaderboard

import io.ktor.server.application.*
import io.ktor.server.routing.*

fun Application.leaderboardRouting() {
    routing {

        post("/leaderboard") {  }

        get("/leaderboard/{startDate?}") {  }

        get("/leaderboard") {  }

        patch("/leaderboard/{id?}") {  }

        put("/leaderboard/{id?}") {  }

        delete("leaderboard/{id?}") {  }

    }
}
