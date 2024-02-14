package com.kontinum.repository

import com.kontinum.model.purchase.Purchase
import com.kontinum.service.purchase.PurchaseService
import com.kontinum.service.purchase.dto.PurchaseCreateDTO
import com.kontinum.service.purchase.dto.PurchasePatchDTO

class PurchaseRepository(private val purchaseService: PurchaseService) {
    suspend fun createPurchase(data: PurchaseCreateDTO): Purchase? {
        return this.purchaseService.createPurchase(data)
    }

    suspend fun getPurchase(purchaseId: Int): Purchase? {
        return this.purchaseService.getPurchase(purchaseId)
    }

    suspend fun getPurchases(businessId: Int): List<Purchase> {
        return this.purchaseService.getPurchases(businessId)
    }

    suspend fun getPurchasesByUserId(userId: Int): List<Purchase> {
        return this.purchaseService.getPurchaseByUserId(userId)
    }

    suspend fun patchPurchase(purchaseId: Int, data: PurchasePatchDTO): Int {
        return this.purchaseService.patchPurchase(purchaseId, data)
    }

    suspend fun deletePurchase(purchaseId: Int): Unit {
        return this.purchaseService.deletePurchase(purchaseId)
    }
}