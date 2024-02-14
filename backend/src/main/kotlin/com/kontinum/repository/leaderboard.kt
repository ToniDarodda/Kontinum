package com.kontinum.repository

import com.kontinum.model.leaderboard.Leaderboard
import com.kontinum.model.leaderboard.LeaderboardDetail
import com.kontinum.service.leaderboard.LeaderboardServices
import com.kontinum.service.leaderboard.dto.LeaderboardCreateDTO
import com.kontinum.service.leaderboard.dto.LeaderboardDetailsCreateDTO
import com.kontinum.service.leaderboard.dto.LeaderboardPatchDTO

class LeaderboardRepository(private val leaderboardServices: LeaderboardServices) {
    suspend fun createLeaderboard(data: LeaderboardCreateDTO): Leaderboard? {
        return this.leaderboardServices.createLeaderboard(data)
    }

    suspend fun createLeaderboardDetail(data: LeaderboardDetailsCreateDTO): LeaderboardDetail? {
        return this.leaderboardServices.createLeaderboardDetails(data)
    }

    suspend fun getLeaderboardDetailsByUserId(userId: Int): List<LeaderboardDetail> {
        return this.leaderboardServices.getLeaderboardDetails(userId)
    }

    suspend fun getLeaderboard(leaderboardId: Int): Leaderboard? {
        return this.leaderboardServices.getLeaderboard(leaderboardId)
    }

    suspend fun patchLeaderBoard(leaderboardId: Int, leaderboardPatchDTO: LeaderboardPatchDTO): Int {
        return this.leaderboardServices.patchLeaderboard(leaderboardId, leaderboardPatchDTO)
    }

    suspend fun deleteLeaderboard(leaderboardId: Int): Unit {
        return this.leaderboardServices.deleteLeaderboard(leaderboardId)
    }


}