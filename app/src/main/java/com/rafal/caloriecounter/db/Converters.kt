package com.rafal.caloriecounter.db

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.rafal.caloriecounter.data.Nutrient
import com.rafal.caloriecounter.data.Nutrients
import java.util.*

private const val SEPARATOR = ";;"

class Converters {
    @TypeConverter
    fun stringToNutrients(value: String): Nutrients {
        return Gson().fromJson(value, Nutrients::class.java)
    }

    @TypeConverter
    fun nutrientsToString(nutrients: Nutrients): String {
        return Gson().toJson(nutrients)
    }
}