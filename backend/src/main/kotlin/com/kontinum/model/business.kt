package com.kontinum.model

import kotlinx.serialization.Serializable
import org.jetbrains.exposed.sql.Table
import java.time.LocalDateTime


object Business : Table() {
    val id = integer("id").autoIncrement()
    val businessName = varchar("businessName", 255)
    val businessLocation = varchar("businessLocation", 255)
    val businessLegalInformation = varchar("businessLegalInformation", 255)
    val businessPhoneNumber = varchar("businessPhoneNumber", 15)
    val businessEmail = varchar("businessEmail", 255).uniqueIndex()
    val password = varchar("password", 255)
    val createdAt = varchar("createdAt", 255).default(LocalDateTime.now().toString())

    override val primaryKey = PrimaryKey(id)

    init {
        uniqueIndex(businessEmail, businessName, businessPhoneNumber, businessLegalInformation)
    }
}

@Serializable
data class BusinessData(val id: Int, val businessName: String, val businessLocation: String, val businessLegalInformation: String, val businessPhoneNumber: String, val businessEmail: String, val password: String)