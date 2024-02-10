package com.kontinum.model.purchase

import com.kontinum.model.Cocktails
import org.jetbrains.exposed.sql.ReferenceOption
import org.jetbrains.exposed.sql.Table

object PurchaseDetails : Table() {
    val id = integer("id").autoIncrement()

    val purchaseId = reference("purchaseId", Purchases.id).references(Purchases.id, onDelete = ReferenceOption.CASCADE)

    val cocktailId = reference("cocktailId", Cocktails.id).references(Cocktails.id, onDelete = null)

    val purchasePrice = integer("purchasePrice")

    override val primaryKey = PrimaryKey(id)
}