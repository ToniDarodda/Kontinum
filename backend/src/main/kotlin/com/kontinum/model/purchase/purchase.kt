package com.kontinum.model.purchase

import com.kontinum.model.Business
import com.kontinum.model.Users
import kotlinx.serialization.Serializable
import org.jetbrains.exposed.sql.ReferenceOption
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.javatime.datetime
import java.time.LocalDateTime

object Purchases : Table() {
    val id = integer("id").autoIncrement()

    val userId = integer("userId").references(Users.id, onDelete = null)

    val businessId = integer("businessId").references(Business.id, onDelete = ReferenceOption.CASCADE)

    private val purchaseTime = datetime("purchaseTime").default(LocalDateTime.now())

    val totalPrice = integer("totalPrice")

    override val primaryKey = PrimaryKey(id)
}
@Serializable
data class Purchase(val id: Int, val userId: Int, val totalPrice: Int, val businessId: Int)