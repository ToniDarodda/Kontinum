package com.kontinum.model.leaderboard

import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.javatime.datetime
import java.time.LocalDateTime

object Leaderboards : Table() {
    val id = integer("id").autoIncrement()

    val startTime = varchar("startTime", 255).default(LocalDateTime.now().toString())

    val endTime = varchar("endTime", 255)

    override val primaryKey = PrimaryKey(id)
}

@Serializable
data class Leaderboard(val id: Int, val startTime: String, val endTime: String)