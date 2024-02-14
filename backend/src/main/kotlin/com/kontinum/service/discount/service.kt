package com.kontinum.service.discount

import com.kontinum.model.Discount
import com.kontinum.model.Discounts
import com.kontinum.service.discount.dto.DiscountCreateDTO
import com.kontinum.service.discount.dto.DiscountPatchDTO
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.transactions.transaction

class DiscountService : DiscountInterface {

    private fun rowToDiscount(row: ResultRow) = Discount(
        id = row[Discounts.id],
        discountPerServing = row[Discounts.discountPerServing],
        onSeveral = row[Discounts.onSeveral],
        businessId = row[Discounts.businessId]
    )
    override suspend fun createDiscount(data: DiscountCreateDTO): Discount? {
        return transaction {
            val insertedDiscount = Discounts.insert {
                it[discountPerServing] = data.discountPerServing
                it[onSeveral] = data.onSeveral
                it[businessId] = data.businessId
            }

            insertedDiscount.resultedValues?.singleOrNull()?.let(::rowToDiscount)
        }
    }

    override suspend fun getDiscount(businessId: Int): List<Discount> {
        return transaction {
            val retrievedDiscounts = Discounts.selectAll().where { Discounts.businessId eq businessId }

            retrievedDiscounts.map(::rowToDiscount)
        }
    }

    override suspend fun patchDiscount(discountId: Int, data: DiscountPatchDTO): Int {
        return transaction {
            Discounts.update({ Discounts.id eq discountId }) {
                data.discountPerServing?.let { nonNullDiscountPerServing ->
                    it[discountPerServing] = nonNullDiscountPerServing
                }

                data.onSeveral?.let { nonNullOnSeveral ->
                    it[onSeveral] = nonNullOnSeveral
                }
            }
        }
    }

    override suspend fun deleteDiscount(discountId: Int) {
        transaction {
            Discounts.deleteWhere { Discounts.id eq discountId }
        }
    }

}
