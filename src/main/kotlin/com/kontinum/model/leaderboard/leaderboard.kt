package com.kontinum.model.leaderboard

import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.javatime.datetime
import java.time.LocalDateTime

object Leaderboards : Table() {
    val id = integer("id").autoIncrement()

    val startTime = datetime("startTime").default(LocalDateTime.now())

    val endTime = datetime("endTime")

    override val primaryKey = PrimaryKey(id)
}