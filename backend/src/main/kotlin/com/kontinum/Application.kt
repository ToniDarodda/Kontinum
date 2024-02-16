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
import com.kontinum.util.auth
import com.kontinum.util.contentNegotiation
import com.kontinum.util.cors
import io.ktor.server.engine.*
import io.ktor.server.netty.*

fun main() {
    embeddedServer(Netty, port = 8080, host = "localhost") {

        DatabaseSingleton.init()

        val businessService = BusinessService()
        val businessRepository = BusinessRepository(businessService)

        val userService = UserService()
        val userRepository = UserRepositoryImpl(userService)

        val cocktailService = CocktailService()
        val cocktailRepository = CocktailRepositoryImpl(cocktailService)

        val stockService = StockService()
        val stockRepository = StockRepository(stockService)

        val discountService = DiscountService()
        val discountRepository = DiscountRepository(discountService)

        val purchaseService = PurchaseService()
        val purchaseRepository = PurchaseRepository(purchaseService)

        val purchaseDetailService = PurchaseDetailsService()
        val purchaseDetailRepository = PurchaseDetailRepository(purchaseDetailService)

        val leaderboardServices = LeaderboardServices()
        val leaderboardRepository = LeaderboardRepository(leaderboardServices)

        cors()
        auth()
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