package com.kontinum.repository

import com.kontinum.model.Cocktail
import com.kontinum.service.cocktail.CocktailService
import com.kontinum.service.cocktail.dto.CocktailCreateDTO
import com.kontinum.service.cocktail.dto.CocktailPatchDTO

class CocktailRepositoryImpl(private val cocktailService: CocktailService) {
    suspend fun createCocktail(data: CocktailCreateDTO): Cocktail? {
        return this.cocktailService.createCocktail(data)
    }

    suspend fun getCocktails(): List<Cocktail> {
        return this.cocktailService.getCocktails()
    }

    suspend fun getCocktail(cocktailId: Int): Cocktail? {
        return this.cocktailService.getCocktail(cocktailId)
    }

    suspend fun patchCocktail(cocktailId: Int, data: CocktailPatchDTO): Int {
        return this.cocktailService.patchCocktail(cocktailId, data)
    }

    suspend fun deleteCocktail(cocktailId: Int): Unit {
        this.cocktailService.deleteCocktail(cocktailId)
    }

}