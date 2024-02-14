package com.kontinum.service.stock

import com.kontinum.model.Stock
import com.kontinum.service.stock.dto.StockCreateDTO
import com.kontinum.service.stock.dto.StockPatchDTO

interface StockInterface {
    suspend fun createStock(data: StockCreateDTO): Stock?
    suspend fun getStock(stockId: Int): Stock?

    suspend fun getStocks(): List<Stock>
    suspend fun patchStock(stockId: Int, data: StockPatchDTO): Int
    suspend fun deleteStock(stockId: Int): Unit
}