package com.kontinum.repository

import com.kontinum.model.Stock
import com.kontinum.service.stock.StockService
import com.kontinum.service.stock.dto.StockCreateDTO
import com.kontinum.service.stock.dto.StockPatchDTO

class StockRepository(private val stockService: StockService) {
    suspend fun createStock(data: StockCreateDTO): Stock? {
        return this.stockService.createStock(data)
    }

    suspend fun getStock(stockId: Int): Stock? {
        return this.stockService.getStock(stockId)
    }

    suspend fun getStocks(): List<Stock> {
        return this.stockService.getStocks()
    }

    suspend fun patchStock(stockId: Int, data: StockPatchDTO): Int {
        return this.stockService.patchStock(stockId, data)
    }

    suspend fun deleteStock(stockId: Int): Unit {
        return this.stockService.deleteStock(stockId)
    }


}