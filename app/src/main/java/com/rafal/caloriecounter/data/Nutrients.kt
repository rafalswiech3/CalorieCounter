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

    fun updateAll(amount: Int) {
        getCalories().amount *= (amount / 100.0f)
        getFat().amount *= (amount / 100.0f)
        getProtein().amount *= (amount / 100.0f)
        getCarbs().amount *= (amount / 100.0f)
    }
}

