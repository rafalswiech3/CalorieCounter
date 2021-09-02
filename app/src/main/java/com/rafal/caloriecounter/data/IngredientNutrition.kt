package com.rafal.caloriecounter.data

data class IngredientNutrition(
    val nutrients: List<Nutrient>
) {
    data class Nutrient(
        val name: String,
        val amount: Float,
        val unit: String
    )
}

