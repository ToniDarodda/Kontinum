package com.kontinum.repository

import com.kontinum.model.Cocktail
import com.kontinum.service.cocktail.CocktailService
import com.kontinum.service.cocktail.dto.*

class CocktailRepositoryImpl(private val cocktailService: CocktailService) {
    suspend fun createCocktail(data: CocktailCreateDTO, businessId: Int): Cocktail? {
        return this.cocktailService.createCocktail(data, businessId)
    }

    suspend fun isCocktailNameExist(cocktailName: String): Boolean {
        return this.cocktailService.isCocktailNameExist(cocktailName)
    }

    suspend fun getCocktails(businessId: Int): List<Cocktail> {
        return this.cocktailService.getCocktails(businessId)
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