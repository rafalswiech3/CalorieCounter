package com.rafal.caloriecounter.data

data class Nutrients(
    val nutrients: List<Nutrient>
) {
    fun getCalories() = nutrients.first {
        it.name == "Calories"
    }

    fun getFat() = nutrients.first {
        it.name == "Fat"
    }

    fun getProtein() = nutrients.first {
        it.name == "Protein"
    }

    fun getCarbs() = nutrients.first {
        it.name == "Carbohydrates"
    }
}

