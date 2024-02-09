package com.kontinum.service.cocktail

import com.kontinum.model.Cocktail
import com.kontinum.service.cocktail.dto.CocktailCreateDTO
import com.kontinum.service.cocktail.dto.CocktailPatchDTO

interface CocktailInterface {
    suspend fun createCocktail(data: CocktailCreateDTO): Cocktail?
    suspend fun getCocktail(cocktailId: Int): Cocktail?
    suspend fun getCocktails(): List<Cocktail>
    suspend fun patchCocktail(cocktailId: Int, data: CocktailPatchDTO): Int
    suspend fun deleteCocktail(cocktailId: Int): Unit
}