package com.rafal.caloriecounter.repos

import com.rafal.caloriecounter.data.IngredientSearch
import com.rafal.caloriecounter.db.IngredientsDao
import javax.inject.Inject

class DailyRepository @Inject constructor(
    private val ingredientDao: IngredientsDao
) {
    suspend fun loadProducts(mealID: Int): List<IngredientSearch> {
        return ingredientDao.loadByMeal(mealID)
    }

    suspend fun removeProduct(ingredient: IngredientSearch) {
        ingredientDao.delete(ingredient)
    }
}