package com.kontinum.database

import com.kontinum.model.Cocktails
import com.kontinum.model.Discounts
import com.kontinum.model.Stocks
import com.kontinum.model.Users
import com.kontinum.model.leaderboard.LeaderboardDetails
import com.kontinum.model.leaderboard.Leaderboards
import com.kontinum.model.purchase.PurchaseDetails
import com.kontinum.model.purchase.Purchases
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction

object DatabaseSingleton {
    fun init() {
        Database.connect("jdbc:postgresql://localhost/kontinuum", driver = "org.h2.Driver", user = "postgres", password = "pass")
        transaction {
            addLogger(StdOutSqlLogger)
            SchemaUtils.create(Users, Cocktails, Discounts, Stocks, Purchases, PurchaseDetails, Leaderboards, LeaderboardDetails)
            SchemaUtils.createMissingTablesAndColumns(Users, Cocktails, Discounts, Stocks, Purchases, PurchaseDetails, Leaderboards, LeaderboardDetails)
        }
    }


}