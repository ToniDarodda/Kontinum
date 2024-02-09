package com.kontinum.routing.core.purchase

import io.ktor.server.application.*
import io.ktor.server.routing.*

fun Application.purchaseRouting() {
    routing {

        post("/purchase") {  }

        get("/purchase") {  }

        get("/purchase/{id?}") {  }

        patch("/purchase/{id?}") {  }

        put("/purchase/{id?}") {  }

        delete("/purchase/{id?}") {  }
    }
}