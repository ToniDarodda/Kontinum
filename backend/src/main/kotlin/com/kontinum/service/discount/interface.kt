package com.kontinum.service.discount

import com.kontinum.model.Discount
import com.kontinum.service.discount.dto.*

interface DiscountInterface {
    suspend fun createDiscount(data: DiscountCreateDTO, businessId: Int): Discount?

    suspend fun getDiscount(businessId: Int): List<Discount>

    suspend fun patchDiscount(discountId: Int, data: DiscountPatchDTO): Int

    suspend fun deleteDiscount(discountId: Int): Unit
}