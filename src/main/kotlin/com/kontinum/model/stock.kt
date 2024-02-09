package com.kontinum.model

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