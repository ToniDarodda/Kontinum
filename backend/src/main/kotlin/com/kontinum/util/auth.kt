package com.kontinum.util

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import io.ktor.http.auth.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*

fun Application.auth() {
    val secret = "Z+MU@YqP+jwXVf&jQ&U#((q7V5tWc(a^n6H)7MVUNDdaNp7QeUHd^)@hCLSW+"
    val issuer = "http://localhost:8080"
    val audience = "Kontinum"
    val myRealm = "Kontinum"

    install(Authentication) {
        jwt("auth-jwt") {
            realm = myRealm
            val jwtAlgorithm = Algorithm.HMAC256(secret)
            verifier(JWT.require(jwtAlgorithm).withIssuer(issuer).build())

            authHeader { call ->
                val cookieValue = call.request.cookies["Authorization"] ?: return@authHeader null

                try {
                    parseAuthorizationHeader("Bearer $cookieValue")
                } catch (cause: IllegalArgumentException) {
                    cause.message
                    null
                }
            }

            validate { credential ->
                val userId = credential.payload.getClaim("userId").asInt()
                if (userId != null && userId > 0) {
                    JWTPrincipal(credential.payload)
                } else {
                    null
                }
            }


        }
    }
}