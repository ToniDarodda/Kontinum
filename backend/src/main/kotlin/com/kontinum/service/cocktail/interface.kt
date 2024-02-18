package com.kontinum.service.cocktail

import com.kontinum.model.Cocktail
import com.kontinum.service.cocktail.dto.*

interface CocktailInterface {
    suspend fun createCocktail(data: CocktailCreateDTO, businessId: Int): Cocktail?
    suspend fun getCocktail(cocktailId: Int): Cocktail?
    suspend fun getCocktails(businessId: Int): List<Cocktail>
    suspend fun patchCocktail(cocktailId: Int, data: CocktailPatchDTO): Int
    suspend fun deleteCocktail(cocktailId: Int): Unit
}