package com.kontinum.model.purchase

import com.kontinum.model.Users
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.javatime.datetime
import java.time.LocalDateTime

object Purchases : Table() {
    val id = integer("id").autoIncrement()

    val userId = reference("userId", Users.id)

    val purchaseTime = datetime("purchaseTime").default(LocalDateTime.now())

    val totalPrice = integer("totalPrice")

    override val primaryKey = PrimaryKey(id)
}