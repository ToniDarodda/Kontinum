package com.kontinum.model.purchase

import com.kontinum.model.Cocktails
import org.jetbrains.exposed.sql.Table

object PurchaseDetails : Table() {
    val id = integer("id").autoIncrement()

    val purchaseId = reference("purchaseId", Purchases.id)

    val cocktailId = reference("cocktailId", Cocktails.id)

    val purchasePrice = integer("purchasePrice")

    override val primaryKey = PrimaryKey(id)
}