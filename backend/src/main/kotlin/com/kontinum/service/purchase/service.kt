package com.kontinum.service.purchase

import com.kontinum.model.purchase.Purchase
import com.kontinum.model.purchase.Purchases
import com.kontinum.service.purchase.dto.PurchaseCreateDTO
import com.kontinum.service.purchase.dto.PurchasePatchDTO
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.transactions.transaction

class PurchaseService : PurchaseInterface {

    private fun rowToPurchase(row: ResultRow) = Purchase(
        id = row[Purchases.id],
        userId = row[Purchases.userId],
        totalPrice = row[Purchases.totalPrice],
        businessId = row[Purchases.businessId]
    )
    override suspend fun createPurchase(data: PurchaseCreateDTO): Purchase? {
        return transaction {
            val insertedPurchase = Purchases.insert {
                it[totalPrice] = data.totalPrice
                it[userId] = data.userId
                it[businessId] = data.businessId
            }

            insertedPurchase.resultedValues?.singleOrNull()?.let(::rowToPurchase)
        }
    }

    override suspend fun getPurchase(purchaseId: Int): Purchase? {
        return transaction {
            val retrievedPurchase = Purchases.selectAll().where { Purchases.id eq purchaseId }

            retrievedPurchase.singleOrNull()?.let(::rowToPurchase)
        }
    }

    override suspend fun getPurchaseByUserId(userId: Int): List<Purchase> {
        return transaction {
            val retrievedPurchases = Purchases.selectAll().where { Purchases.userId eq userId }

            retrievedPurchases.map(::rowToPurchase)
        }
    }

    override suspend fun getPurchases(businessId: Int): List<Purchase> {
        return transaction {
            val retrievedPurchases = Purchases.selectAll().where { Purchases.businessId eq businessId }

            retrievedPurchases.map(::rowToPurchase)
        }
    }

    override suspend fun patchPurchase(purchaseId: Int, data: PurchasePatchDTO): Int {
        return transaction {
            Purchases.update({ Purchases.id eq purchaseId}) {

                data.businessId?.let { nonNullBusinessId ->
                    it[businessId] = nonNullBusinessId
                }

                data.userId?.let { nonNullUserId ->
                    it[userId] = nonNullUserId
                }
                data.totalPrice?.let { nonNullTotalPrice ->
                    it[totalPrice] = nonNullTotalPrice
                }
            }
        }
    }

    override suspend fun deletePurchase(purchaseId: Int) {
        transaction {
            Purchases.deleteWhere { Purchases.id eq purchaseId }
        }
    }

}