package com.kontinum.model.leaderboard

import com.kontinum.model.Business
import kotlinx.serialization.Serializable
import org.jetbrains.exposed.sql.Table
import java.time.LocalDateTime

object Leaderboards : Table() {
    val id = integer("id").autoIncrement()
    val startTime = varchar("startTime", 255).default(LocalDateTime.now().toString())
    val endTime = varchar("endTime", 255)
    val businessId = integer("businessId").references(Business.id)

    override val primaryKey = PrimaryKey(id)
}

@Serializable
data class Leaderboard(val id: Int, val startTime: String, val endTime: String, val businessId: Int)