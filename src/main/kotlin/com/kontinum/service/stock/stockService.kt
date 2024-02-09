package com.kontinum.service.stock

import com.kontinum.service.stock.dto.StockCreateDTO
import com.kontinum.service.stock.dto.StockPatchDTO

class StockService : StockInterface {
    override suspend fun createStock(data: StockCreateDTO) {
    }
    override suspend fun getStock(stockId: Int) {}
    override suspend fun patchStock(stockId: Int, data: StockPatchDTO) {}
    override suspend fun deleteStock(stockId: Int) {}
}