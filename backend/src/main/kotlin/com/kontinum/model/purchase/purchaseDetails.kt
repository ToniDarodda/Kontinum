package com.kontinum.model.purchase

import com.kontinum.model.Cocktails
import kotlinx.serialization.Serializable
import org.jetbrains.exposed.sql.*

object PurchaseDetails : Table() {
    val id = integer("id").autoIncrement()
    val purchaseId = reference("purchaseId", Purchases.id).references(Purchases.id, onDelete = ReferenceOption.CASCADE)
    val cocktailId = reference("cocktailId", Cocktails.id).references(Cocktails.id, onDelete = null)
    val purchasePrice = integer("purchasePrice")

    override val primaryKey = PrimaryKey(id)
}

@Serializable
data class PurchaseDetail(val id: Int, val purchaseId: Int, val cocktailId: Int, val purchasePrice: Int)