package com.kontinum.service.leaderboard

import com.kontinum.model.leaderboard.Leaderboard
import com.kontinum.model.leaderboard.LeaderboardDetail
import com.kontinum.model.leaderboard.LeaderboardDetails
import com.kontinum.model.leaderboard.Leaderboards
import com.kontinum.service.leaderboard.dto.LeaderboardCreateDTO
import com.kontinum.service.leaderboard.dto.LeaderboardDetailsCreateDTO
import com.kontinum.service.leaderboard.dto.LeaderboardPatchDTO
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.transactions.transaction

class LeaderboardServices : LeaderboardInterface {

    private fun rowToLeaderboard(row: ResultRow) = Leaderboard(
        id = row[Leaderboards.id],
        startTime = row[Leaderboards.startTime],
        endTime = row[Leaderboards.endTime],
        businessId = row[Leaderboards.businessId]
    )

    private fun rowToLeaderboardDetail(row: ResultRow) = LeaderboardDetail(
        id = row[LeaderboardDetails.id],
        userId = row[LeaderboardDetails.userId],
        leaderboardId = row[LeaderboardDetails.leaderboardId],
        score = row[LeaderboardDetails.score]
    )
    override suspend fun createLeaderboard(data: LeaderboardCreateDTO, businessId: Int): Leaderboard? {
        return transaction {
            val createdLeaderboard = Leaderboards.insert {
                it[startTime] = data.startTime
                it[endTime] = data.endTime
                it[this.businessId] = businessId
            }

            createdLeaderboard.resultedValues?.singleOrNull()?.let(::rowToLeaderboard)
        }
    }

    override suspend fun createLeaderboardDetails(data: LeaderboardDetailsCreateDTO): LeaderboardDetail? {
        return transaction {
            val createdLeaderboardDetail = LeaderboardDetails.insert {
                it[leaderboardId] = data.leaderboardId
                it[userId] = data.userId
                it[score] = data.score
            }

            createdLeaderboardDetail.resultedValues?.singleOrNull()?.let(::rowToLeaderboardDetail)
        }
    }

    override suspend fun getLeaderboardDetails(userId: Int): List<LeaderboardDetail> {
        return transaction {
            val retrievedLeaderboardDetails = LeaderboardDetails.selectAll().where { LeaderboardDetails.userId eq userId }

            retrievedLeaderboardDetails.map(::rowToLeaderboardDetail)
        }
    }

    override suspend fun getLeaderboard(leaderboardId: Int): Leaderboard? {
        return transaction {
            val retrievedLeaderboard = Leaderboards.selectAll().where { LeaderboardDetails.id eq leaderboardId}

            retrievedLeaderboard.singleOrNull()?.let(::rowToLeaderboard)
        }
    }

    override suspend fun patchLeaderboard(leaderboardId: Int, data: LeaderboardPatchDTO): Int {
        return transaction {
            Leaderboards.update({ Leaderboards. id eq leaderboardId}) {
                data.startTime?.let { nonNullStartTime ->
                    it[startTime] = nonNullStartTime
                }

                data.endTime?.let { nonNullEndTime ->
                    it[endTime] = nonNullEndTime
                }
            }
        }
    }

    override suspend fun deleteLeaderboard(leaderboardId: Int): Unit {
        transaction {
            Leaderboards.deleteWhere { Leaderboards.id eq leaderboardId }
        }
    }

}