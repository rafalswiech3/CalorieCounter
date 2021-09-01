package com.rafal.caloriecounter.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.rafal.caloriecounter.data.IngredientSearch

@Database(entities = [IngredientSearch::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun ingredientDao(): IngredientsDao
}