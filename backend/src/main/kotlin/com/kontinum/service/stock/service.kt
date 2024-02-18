package com.kontinum.service.stock

import com.kontinum.model.Stock
import com.kontinum.model.Stocks
import com.kontinum.service.stock.dto.StockCreateDTO
import com.kontinum.service.stock.dto.StockPatchDTO
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.transactions.transaction

class StockService : StockInterface {
    private fun stockToRow(row: ResultRow) = Stock(
        id = row[Stocks.id],
        capacity = row[Stocks.capacity],
        cocktailId = row[Stocks.cocktailId]
    )
    override suspend fun createStock(data: StockCreateDTO, businessId: Int): Stock? {
        return transaction {
            val createdStock = Stocks.insert {
                it[capacity] = data.capacity
                it[cocktailId] = data.cocktailId
                it[this.businessId] = businessId
            }

            createdStock.resultedValues?.singleOrNull()?.let(::stockToRow)
        }
    }
    override suspend fun getStock(stockId: Int): Stock? {
        return transaction {
            val retrievedStock = Stocks.selectAll().where { Stocks.id eq stockId }
            retrievedStock.singleOrNull()?.let(::stockToRow)
        }
    }
    override suspend fun getStocks(businessId: Int): List<Stock> {
        return transaction {
            val retrievedStocks = Stocks.selectAll().where { Stocks.businessId eq businessId}

            retrievedStocks.map(::stockToRow)
        }
    }
    override suspend fun patchStock(stockId: Int, data: StockPatchDTO): Int {
        return transaction {
            Stocks.update({Stocks.id eq stockId} ) {
                data.capacity?.let { nonNullCapacity ->
                    it[capacity] = nonNullCapacity
                }

                data.cocktailId?.let { nonNullCocktailId ->
                    it[cocktailId] = nonNullCocktailId
                }
            }

        }
    }
    override suspend fun deleteStock(stockId: Int): Unit {
        return transaction {
            Stocks.deleteWhere { Stocks.id eq stockId }
        }
    }
}