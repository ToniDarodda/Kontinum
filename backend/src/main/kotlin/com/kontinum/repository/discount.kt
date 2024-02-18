package com.kontinum.repository

import com.kontinum.model.Discount
import com.kontinum.service.discount.DiscountService
import com.kontinum.service.discount.dto.*

class DiscountRepositoryImpl(private val discountService: DiscountService) {
    suspend fun createDiscount(data: DiscountCreateDTO, businessId: Int): Discount? {
        return this.discountService.createDiscount(data, businessId)
    }

    suspend fun getDiscount(businessId: Int): List<Discount> {
        return this.discountService.getDiscount(businessId)
    }

    suspend fun patchDiscount(discountId: Int, data: DiscountPatchDTO): Int {
        return this.discountService.patchDiscount(discountId, data)
    }

    suspend fun deleteDiscount(discountId: Int): Unit {
        this.discountService.deleteDiscount(discountId)
    }


}