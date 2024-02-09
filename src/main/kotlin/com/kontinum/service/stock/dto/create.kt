package com.kontinum.service.stock.dto

import kotlinx.serialization.Serializable

@Serializable
data class StockCreateDTO(val capacity: Int, val cocktailId: Int)
