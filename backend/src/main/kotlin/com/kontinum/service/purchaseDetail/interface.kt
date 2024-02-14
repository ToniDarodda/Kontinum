package com.kontinum.service.purchaseDetail

import com.kontinum.model.purchase.Purchase
import com.kontinum.model.purchase.PurchaseDetail
import com.kontinum.model.purchase.PurchaseDetails
import com.kontinum.service.purchaseDetail.dto.PurchaseDetailsCreateDTO

interface PurchaseDetailsInterface {
    suspend fun createPurchaseDetails(data: PurchaseDetailsCreateDTO): PurchaseDetail?

    suspend fun getPurchaseDetails(purchaseDetailId: Int): PurchaseDetail?

    suspend fun getPurchaseDetailsByPurchaseId(purchaseId: Int): List<PurchaseDetail>

    suspend fun getPurchaseDetailsByBusinessId(businessId: Int): List<PurchaseDetail?>

    suspend fun deletePurchaseDetails(purchaseDetailId: Int): Unit

}
