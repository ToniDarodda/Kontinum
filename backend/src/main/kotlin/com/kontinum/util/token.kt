package com.kontinum.util

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import java.util.*

class TokenManager(private val audience: String, private val issuer: String, private val secret: String) {

    public fun generateToken(userId: Int): String {
        return JWT.create()
            .withAudience(this.audience)
            .withIssuer(this.issuer)
            .withClaim("userId", userId)
            .withExpiresAt(Date(System.currentTimeMillis() + 6000000))
            .sign(Algorithm.HMAC256(this.secret))
    }
}
