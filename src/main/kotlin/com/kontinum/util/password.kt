package com.kontinum.util

import at.favre.lib.crypto.bcrypt.BCrypt

fun hashPassword(password: String): String {
    return BCrypt.withDefaults().hashToString(12, password.toCharArray())
}

fun checkPassword(hashedPassword: String, plainTextPassword: String): Boolean {
    val verifier = BCrypt.verifyer()
    val result = verifier.verify(plainTextPassword.toCharArray(), hashedPassword)

    return result.verified
}