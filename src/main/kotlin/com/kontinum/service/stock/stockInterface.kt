package com.kontinum.service.stock

import com.kontinum.service.stock.dto.StockCreateDTO
import com.kontinum.service.stock.dto.StockPatchDTO

interface StockInterface {
    suspend fun createStock(data: StockCreateDTO) {}
    suspend fun getStock(stockId: Int) {}
    suspend fun patchStock(stockId: Int, data: StockPatchDTO) {}
    suspend fun deleteStock(stockId: Int) {}
}