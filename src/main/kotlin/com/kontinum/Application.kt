package com.kontinum

import com.kontinum.database.DatabaseSingleton
import com.kontinum.repository.CocktailRepositoryImpl
import com.kontinum.repository.StockRepository
import com.kontinum.repository.UserRepositoryImpl
import com.kontinum.routing.core.cocktailRouting
import com.kontinum.routing.configureRouting
import com.kontinum.routing.core.discountRouting
import com.kontinum.routing.core.leaderboard.leaderboardDetailRouting
import com.kontinum.routing.core.leaderboard.leaderboardRouting
import com.kontinum.routing.core.purchase.purchaseDetail
import com.kontinum.routing.core.purchase.purchaseRouting
import com.kontinum.routing.core.stocksRouting
import com.kontinum.routing.core.userRouting
import com.kontinum.service.cocktail.CocktailService
import com.kontinum.service.stock.StockService
import com.kontinum.service.user.UserService
import com.kontinum.util.contentNegotiation
import io.ktor.server.engine.*
import io.ktor.server.netty.*

fun main() {
    embeddedServer(Netty, port = 8080, host = "localhost") {
        DatabaseSingleton.init()
        val userService = UserService()
        val userRepository = UserRepositoryImpl(userService)

        val cocktailService = CocktailService()
        val cocktailRepository = CocktailRepositoryImpl(cocktailService)

        val stockService = StockService()
        val stockRepository = StockRepository(stockService)

        contentNegotiation()
        configureRouting()
        userRouting(userRepository)
        cocktailRouting(cocktailRepository)
        stocksRouting(stockRepository)
        discountRouting()
        purchaseRouting()
        purchaseDetail()
        leaderboardRouting()
        leaderboardDetailRouting()
    }.start(wait = true)
}