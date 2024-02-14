package com.kontinum.service.cocktail.dto

import kotlinx.serialization.Serializable

@Serializable
data class CocktailCreateDTO(val name: String, val pricePerServing: Int)