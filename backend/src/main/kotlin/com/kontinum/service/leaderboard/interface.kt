package com.kontinum.service.leaderboard

import com.kontinum.model.leaderboard.*
import com.kontinum.service.leaderboard.dto.*

interface LeaderboardInterface {
    suspend fun createLeaderboard(data: LeaderboardCreateDTO, businessId: Int): Leaderboard?

    suspend fun createLeaderboardDetails(data: LeaderboardDetailsCreateDTO): LeaderboardDetail?

    suspend fun getLeaderboardDetails(userId: Int): List<LeaderboardDetail>

    suspend fun getLeaderboard(leaderboardId: Int): Leaderboard?

    suspend fun patchLeaderboard(leaderboardId: Int, data: LeaderboardPatchDTO): Int

    suspend fun deleteLeaderboard(leaderboardId: Int): Unit

}