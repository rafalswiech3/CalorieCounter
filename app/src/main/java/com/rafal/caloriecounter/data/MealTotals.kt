package com.rafal.caloriecounter.data

data class MealTotals(
    var kcal: Float,
    var fats: Float,
    var carbs: Float,
    var proteins: Float
) {
    fun clearAll() {
        kcal = 0f
        fats = 0f
        carbs = 0f
        proteins = 0f
    }

    fun update(kcal: Float, fat: Float, carbs: Float, proteins: Float) {
        this.kcal += kcal
        this.fats += fat
        this.carbs += carbs
        this.proteins += proteins
    }
}