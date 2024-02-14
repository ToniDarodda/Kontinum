package com.kontinum.service.discount.dto

import kotlinx.serialization.Serializable

@Serializable
data class DiscountCreateDTO(val discountPerServing: Float, val onSeveral: Int, val businessId: Int)