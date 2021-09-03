package com.rafal.caloriecounter.db

import androidx.room.*
import com.rafal.caloriecounter.data.IngredientSearch
import java.util.*

@Dao
interface IngredientsDao {
    @Query("SELECT * FROM ingredientsearch WHERE date = (:date) AND meal IN (:meal)")
    suspend fun loadByMeal(meal: Int, date: String): List<IngredientSearch>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(vararg ingredients: IngredientSearch)

    @Delete
    suspend fun delete(ingredient: IngredientSearch)
}