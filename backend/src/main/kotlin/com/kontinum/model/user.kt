package com.kontinum.model

import kotlinx.serialization.Serializable
import org.jetbrains.exposed.sql.*

object Users : Table() {
    val id = integer("id").autoIncrement()
    val firstName=varchar("firstName", 255)
    val lastName=varchar("lastName",255)
    val email=varchar("email", 255).uniqueIndex()
    val businessId = integer("businessId").references(Business.id, onDelete = ReferenceOption.CASCADE)

    override val primaryKey = PrimaryKey(id)

    init {
        uniqueIndex("email", email)
    }
}

@Serializable
data class User(val id: Int, val firstName: String, val lastName: String, val email: String)
