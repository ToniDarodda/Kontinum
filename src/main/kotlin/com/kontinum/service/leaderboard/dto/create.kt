package com.kontinum.service.leaderboard.dto

import kotlinx.serialization.Serializable
import net.sf.jsqlparser.expression.DateTimeLiteralExpression.DateTime

@Serializable
data class LeaderboardCreateDTO(val startTime: String, val endTime: String)

@Serializable
data class LeaderboardDetailsCreateDTO(val leaderboardId: Int, val score: Int, val userId: Int)