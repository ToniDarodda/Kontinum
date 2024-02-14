package com.kontinum.model

import com.kontinum.model.Cocktails.references
import kotlinx.serialization.Serializable
import org.jetbrains.exposed.sql.ReferenceOption
import org.jetbrains.exposed.sql.Table

object Discounts : Table() {
    val id = integer("id").autoIncrement()

    val discountPerServing = float("discountPerServing").default(10F)

    val onSeveral = integer("onSeveral").default(2)

    val businessId = integer("businessId").references(Business.id, onDelete = ReferenceOption.CASCADE)

    override val primaryKey = PrimaryKey(id)
}

@Serializable
data class Discount(val id: Int, val discountPerServing: Float, val onSeveral: Int, val businessId: Int)