package com.kontinum.service.cocktail

import com.kontinum.model.Cocktail
import com.kontinum.model.Cocktails
import com.kontinum.service.cocktail.dto.CocktailCreateDTO
import com.kontinum.service.cocktail.dto.CocktailPatchDTO
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.transactions.transaction

class CocktailService : CocktailInterface {

    private fun cocktailToRow(row: ResultRow) = Cocktail(
        id = row[Cocktails.id],
        name = row[Cocktails.name],
        pricePerGlass = row[Cocktails.pricePerServing]
    )

    override suspend fun createCocktail(data: CocktailCreateDTO): Cocktail? {
        return transaction {
            val createdCocktail = Cocktails.insert {
                it[name] = data.name
                it[pricePerServing] = data.pricePerServing
            }

            createdCocktail.resultedValues?.singleOrNull()?.let(::cocktailToRow)
        }
    }

    override suspend fun getCocktail(cocktailId: Int): Cocktail? {
        return transaction {
            val retrievedCocktail = Cocktails.selectAll().where(Cocktails.id eq cocktailId)

            retrievedCocktail.map(::cocktailToRow).singleOrNull()
        }
    }

    override suspend fun getCocktails(): List<Cocktail> {
        return transaction {
            val retrievedCocktails = Cocktails.selectAll()

            retrievedCocktails.map(::cocktailToRow)
        }
    }

    override suspend fun patchCocktail(cocktailId: Int, data: CocktailPatchDTO): Int {
        return transaction {

            Cocktails.update({Cocktails.id eq cocktailId }) {

                data.name?.let { nonNullName ->
                    it[name] = nonNullName
                }

                data.pricePerServing?.let { nonNullPricePerServing ->
                    it[pricePerServing] = nonNullPricePerServing
                }

            }
        }
    }

    override suspend fun deleteCocktail(cocktailId: Int): Unit {
        transaction {
            Cocktails.deleteWhere{ Cocktails.id eq cocktailId }
        }
    }
}