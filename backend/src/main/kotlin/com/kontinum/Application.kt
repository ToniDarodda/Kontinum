package com.kontinum

import com.kontinum.database.DatabaseSingleton
import com.kontinum.repository.*
import com.kontinum.routing.configureRouting
import com.kontinum.routing.core.*
import com.kontinum.routing.core.leaderboardRouting
import com.kontinum.routing.core.purchase.purchaseDetail
import com.kontinum.routing.core.purchase.purchaseRouting
import com.kontinum.service.business.BusinessService
import com.kontinum.service.cocktail.CocktailService
import com.kontinum.service.discount.DiscountService
import com.kontinum.service.leaderboard.LeaderboardServices
import com.kontinum.service.purchase.PurchaseService
import com.kontinum.service.purchaseDetail.PurchaseDetailsService
import com.kontinum.service.stock.StockService
import com.kontinum.service.user.UserService
import com.kontinum.util.*
import com.typesafe.config.ConfigFactory
import io.ktor.server.engine.*
import io.ktor.server.netty.*

fun main() {
    val config = ConfigFactory.load()
    val hostKey = config.getString("server.host")
    val portKey = config.getString("server.port").toInt()

    val secret = config.getString("jwt.secret")
    val issuer = config.getString("jwt.issuer")
    val audience = config.getString("jwt.audience")

    val tokenManager = TokenManager(audience, issuer, secret)

    embeddedServer(Netty, port = portKey, host = hostKey) {

        DatabaseSingleton.init()

        val businessService = BusinessService(tokenManager)
        val businessRepository = BusinessRepositoryImpl(businessService)

        val userService = UserService()
        val userRepository = UserRepositoryImpl(userService)

        val cocktailService = CocktailService()
        val cocktailRepository = CocktailRepositoryImpl(cocktailService)

        val stockService = StockService()
        val stockRepository = StockRepositoryImpl(stockService)

        val discountService = DiscountService()
        val discountRepository = DiscountRepositoryImpl(discountService)

        val purchaseService = PurchaseService()
        val purchaseRepository = PurchaseRepositoryImpl(purchaseService)

        val purchaseDetailService = PurchaseDetailsService()
        val purchaseDetailRepository = PurchaseDetailRepositoryImpl(purchaseDetailService)

        val leaderboardServices = LeaderboardServices()
        val leaderboardRepository = LeaderboardRepositoryImpl(leaderboardServices)

        cors()
        auth(secret, issuer)
        contentNegotiation()
        configureRouting()

        businessRouting(businessRepository)
        userRouting(userRepository)
        cocktailRouting(cocktailRepository)
        stocksRouting(stockRepository)
        discountRouting(discountRepository)
        purchaseRouting(purchaseRepository)
        purchaseDetail(purchaseDetailRepository)
        leaderboardRouting(leaderboardRepository)

    }.start(wait = true)
}