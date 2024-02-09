package com.kontinum.routing.core

import io.ktor.server.application.*
import io.ktor.server.routing.*

fun Application.discountRouting() {
    routing {

        post("/discount") {  }

        get("/discount") {  }

        get("/discount/{id?}") {  }

        patch("/discount/{id?}") {  }

        put("/discount/{id?}") {  }

        delete("/discount/{id?}") {  }
    }
}