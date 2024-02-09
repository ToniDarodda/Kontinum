package com.kontinum.model.leaderboard

import com.kontinum.model.Users
import org.jetbrains.exposed.sql.Table

object LeaderboardDetails : Table() {
    val id = integer("id").autoIncrement()

    val leaderboardId = reference("leaderboardId", Leaderboards.id)

    val score = integer("score")

    val userId = reference("userId", Users.id)

    override val primaryKey = PrimaryKey(id)
}