package com.rafal.caloriecounter.db

import androidx.room.*
import com.rafal.caloriecounter.data.IngredientSearch

@Dao
interface IngredientsDao {
    @Query("SELECT * FROM ingredientsearch WHERE meal IN (:meal)")
    suspend fun loadByMeal(meal: Int): List<IngredientSearch>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(vararg ingredients: IngredientSearch)

    @Delete
    suspend fun delete(ingredient: IngredientSearch)
}