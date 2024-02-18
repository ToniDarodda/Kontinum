package com.kontinum.model

import kotlinx.serialization.Serializable
import org.jetbrains.exposed.sql.Table

object Cocktails : Table() {
    val id = integer("id").autoIncrement()
    val name = varchar("name", 255)
    val pricePerServing = integer("pricePerServing")
    val businessId = integer("businessId").references(Business.id)

    override val primaryKey = PrimaryKey(id)

    init {
        uniqueIndex("name", name)
    }
}

@Serializable
data class Cocktail(val id: Int, val name: String, val pricePerGlass: Int, val businessId: Int)