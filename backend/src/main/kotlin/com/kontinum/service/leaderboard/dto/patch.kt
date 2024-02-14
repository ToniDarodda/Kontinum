package com.kontinum.service.leaderboard.dto

import kotlinx.serialization.Serializable

@Serializable
data class LeaderboardPatchDTO(val startTime: String?, val endTime: String?)

@Serializable
data class LeaderboardDetailsPatchDTO(val leaderboardId: Int?, val score: Int?, val userId: Int?)