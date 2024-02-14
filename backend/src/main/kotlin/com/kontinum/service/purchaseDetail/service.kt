package com.kontinum.service.purchaseDetail

import com.kontinum.model.purchase.Purchase
import com.kontinum.model.purchase.PurchaseDetail
import com.kontinum.model.purchase.PurchaseDetails
import com.kontinum.model.purchase.Purchases
import com.kontinum.service.purchaseDetail.dto.PurchaseDetailsCreateDTO
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.deleteWhere
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction

class PurchaseDetailsService : PurchaseDetailsInterface {
    private fun rowToPurchase(row: ResultRow) = Purchase(
        id = row[Purchases.id],
        userId = row[Purchases.userId],
        totalPrice = row[Purchases.totalPrice],
        businessId = row[Purchases.businessId]
    )
    private fun rowToPurchaseDetails(row: ResultRow) = PurchaseDetail(
        id = row[PurchaseDetails.id],
        purchaseId = row[PurchaseDetails.purchaseId],
        purchasePrice = row[PurchaseDetails.purchasePrice],
        cocktailId = row[PurchaseDetails.cocktailId]
    )
    override suspend fun createPurchaseDetails(data: PurchaseDetailsCreateDTO): PurchaseDetail? {
        return transaction {
            val createdPurchaseDetail = PurchaseDetails.insert {
                it[purchaseId] = data.purchaseId
                it[purchasePrice] = data.purchasePrice
                it[cocktailId] = data.cocktailId
            }

            createdPurchaseDetail.resultedValues?.singleOrNull()?.let(::rowToPurchaseDetails)
        }
    }

    override suspend fun getPurchaseDetails(purchaseDetailId: Int): PurchaseDetail? {
        return transaction {
            val retrievedPurchaseDetail = PurchaseDetails.selectAll().where { PurchaseDetails.id eq purchaseDetailId }

            retrievedPurchaseDetail.singleOrNull()?.let(::rowToPurchaseDetails)
        }
    }

    override suspend fun getPurchaseDetailsByPurchaseId(purchaseId: Int): List<PurchaseDetail> {
        return transaction {
            val retrievedPurchaseDetail = PurchaseDetails.selectAll().where { PurchaseDetails.purchaseId eq purchaseId }

            retrievedPurchaseDetail.map(::rowToPurchaseDetails)
        }
    }

    override suspend fun getPurchaseDetailsByBusinessId(businessId: Int): List<PurchaseDetail?> {
        return transaction {
            val retrievedPurchases = Purchases.selectAll().where { Purchases.businessId eq businessId }

            val mappedRetrievedPurchases = retrievedPurchases.map(::rowToPurchase)
            val retrievedPurchaseDetails = mappedRetrievedPurchases.map {
                PurchaseDetails.selectAll().where { PurchaseDetails.purchaseId eq it.id}
            }

            retrievedPurchaseDetails.map {
                it.singleOrNull()?.let(::rowToPurchaseDetails)
            }
        }
    }

    override suspend fun deletePurchaseDetails(purchaseDetailId: Int) {
        transaction {
            PurchaseDetails.deleteWhere { PurchaseDetails.id eq purchaseDetailId }
        }
    }

}
