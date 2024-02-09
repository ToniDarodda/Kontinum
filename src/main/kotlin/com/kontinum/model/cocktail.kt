package com.kontinum.model

import kotlinx.serialization.Serializable
import org.jetbrains.exposed.sql.Table

object Cocktails : Table() {
    val id = integer("id").autoIncrement()

    val name = varchar("name", 255).uniqueIndex()

    val pricePerServing = integer("pricePerServing")

    override val primaryKey = PrimaryKey(id)

    init {
        uniqueIndex("name", name)
    }
}

@Serializable
data class Cocktail(val id: Int, val name: String, val pricePerGlass: Int)