package com.kontinum.service.discount.dto

import kotlinx.serialization.Serializable

@Serializable
data class DiscountPatchDTO(val discountPerServing: Float?, val onSeveral: Int?)