package com.kontinum.util

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*
import io.ktor.server.response.*

fun Application.auth() {

    val secret = "Z+MU@YqP+jwXVf&jQ&U#((q7V5tWc(a^n6H)7MVUNDdaNp7QeUHd^)@hCLSW+"
    val issuer = "auth-service@kontinum.com"
    val audience = "Kontinum"
    val myRealm = "kbjwd12109f0f-fdwdp124jmwlpkd32"

    install(Authentication) {
        jwt("auth-jwt") {

            realm = myRealm

            verifier(JWT
                .require(Algorithm.HMAC256(secret))
                .withAudience(audience)
                .withIssuer(issuer)
                .build()
            )

            challenge { defaultScheme, realm ->
                call.respond(HttpStatusCode.Unauthorized, "Token is invalid or has expired!")
            }

            validate { credential ->
                if (credential.payload.getClaim("userId").asString() != "") {
                    JWTPrincipal(credential.payload)
                } else {
                    null
                }
            }

        }
    }
}