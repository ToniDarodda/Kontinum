package com.kontinum.repository

import com.kontinum.model.purchase.PurchaseDetail
import com.kontinum.service.purchaseDetail.PurchaseDetailsService
import com.kontinum.service.purchaseDetail.dto.*

class PurchaseDetailRepositoryImpl(private val purchaseDetailsService: PurchaseDetailsService) {
    suspend fun createPurchaseDetail(data: PurchaseDetailsCreateDTO): PurchaseDetail? {
        return this.purchaseDetailsService.createPurchaseDetails(data)
    }

    suspend fun getPurchaseDetail(purchaseDetailsId: Int): PurchaseDetail? {
        return this.purchaseDetailsService.getPurchaseDetails(purchaseDetailsId)
    }

    suspend fun getPurchaseDetailByPurchaseId(purchaseId: Int): List<PurchaseDetail> {
        return this.purchaseDetailsService.getPurchaseDetailsByPurchaseId(purchaseId)
    }

    suspend fun getPurchaseDetailsByBusinessId(businessId: Int): List<PurchaseDetail?> {
        return this.purchaseDetailsService.getPurchaseDetailsByBusinessId(businessId)
    }

    suspend fun deletePurchaseDetail(purchaseDetailsId: Int): Unit {
        return this.purchaseDetailsService.deletePurchaseDetails(purchaseDetailsId)
    }
}