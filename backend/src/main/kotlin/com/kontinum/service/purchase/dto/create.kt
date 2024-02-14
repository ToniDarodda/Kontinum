package com.kontinum.service.purchase.dto

import kotlinx.serialization.Serializable
@Serializable
data class PurchaseCreateDTO(val userId: Int, val totalPrice: Int, val businessId: Int)