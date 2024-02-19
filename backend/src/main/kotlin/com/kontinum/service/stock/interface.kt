package com.kontinum.service.stock

import com.kontinum.model.Stock
import com.kontinum.service.stock.dto.*

interface StockInterface {
    suspend fun createStock(data: StockCreateDTO, businessId: Int): Stock?
    suspend fun getStock(stockId: Int): Stock?

    suspend fun getStocks(businessId: Int): List<Stock>
    suspend fun patchStock(stockId: Int, data: StockPatchDTO): Int
    suspend fun deleteStock(stockId: Int): Unit
}