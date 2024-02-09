package com.kontinum.model

import org.jetbrains.exposed.sql.Table

object Discounts : Table() {
    val id = integer("id").autoIncrement()

    val discountPerServing = float("discountPerServing").default(10F)

    val onSeveral = integer("onSeveral").default(2)

    override val primaryKey = PrimaryKey(id)
}