package com.kontinum.service.purchase

import com.kontinum.model.purchase.Purchase
import com.kontinum.service.purchase.dto.*

interface PurchaseInterface {
    suspend fun createPurchase(data: PurchaseCreateDTO): Purchase?

    suspend fun getPurchase(purchaseId: Int): Purchase?

    suspend fun getPurchaseByUserId(userId: Int): List<Purchase>

    suspend fun getPurchases(businessId: Int): List<Purchase>

    suspend fun patchPurchase(purchaseId: Int, data: PurchasePatchDTO): Int

    suspend fun deletePurchase(purchaseId: Int): Unit
}