package com.kontinum.model

import kotlinx.serialization.Serializable
import org.jetbrains.exposed.sql.Table

object Stocks : Table() {
    val id = integer("id").autoIncrement()

    val capacity = integer("capacity").default(0)


    val cocktailId = reference("cocktailId", Cocktails.id).uniqueIndex()

    override val primaryKey = PrimaryKey(id)

    init {
        uniqueIndex("cocktailId", cocktailId)
    }
}

@Serializable
data class Stock(val id: Int, val capacity: Int, val cocktailId: Int)