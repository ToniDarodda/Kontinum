package com.kontinum.routing.core.purchase

import io.ktor.server.application.*
import io.ktor.server.routing.*

fun Application.purchaseDetail() {
    routing {

        post("/purchaseDetail") {  }

        get("/purchaseDetail") {  }

        get("/purchaseDetail/{id?}") {}

        patch("/purchaseDetail/{id?}") {  }

        put("/purchaseDetail/{id?}") {  }

        delete("/purchaseDetail/{id?}") {  }
    }
}
