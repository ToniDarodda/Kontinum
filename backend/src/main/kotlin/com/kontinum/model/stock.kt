package com.kontinum.model

import kotlinx.serialization.Serializable
import org.jetbrains.exposed.sql.*

object Stocks : Table() {
    val id = integer("id").autoIncrement()
    val capacity = integer("capacity").default(0)
    val cocktailId = reference("cocktailId", Cocktails.id).references(Cocktails.id, onDelete = ReferenceOption.CASCADE)
    val businessId = integer("businessId").references(Business.id)

    override val primaryKey = PrimaryKey(id)

    init {
        uniqueIndex("cocktailId", cocktailId)
    }
}

@Serializable
data class Stock(val id: Int, val capacity: Int, val cocktailId: Int)