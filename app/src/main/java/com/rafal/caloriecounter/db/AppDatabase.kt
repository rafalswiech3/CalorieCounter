package com.rafal.caloriecounter.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.rafal.caloriecounter.data.IngredientSearch

@Database(entities = [IngredientSearch::class], version = 1)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun ingredientDao(): IngredientsDao
}