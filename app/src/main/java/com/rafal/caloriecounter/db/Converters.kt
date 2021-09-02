package com.rafal.caloriecounter.db

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.rafal.caloriecounter.data.Nutrient
import com.rafal.caloriecounter.data.Nutrients

private const val SEPARATOR = ";;"

class Converters {
//    @TypeConverter
//    fun fromNutrientsString(value: String?): List<Nutrient>? {
//        val listOfStrings = value?.split(",")
//        val listOfNutrients = mutableListOf<Nutrient>()
//
//        listOfStrings?.forEach {
//            val dataList = it.split(SEPARATOR)
//            listOfNutrients.add(
//                Nutrient(
//                    name = dataList[0],
//                    amount = dataList[1].toFloat(),
//                    unit = dataList[2]
//                )
//            )
//        }
//
//        return listOfNutrients
//    }
//
//    @TypeConverter
//    fun listOfNutrientsToString(nutrients: List<Nutrient>?): String? {
//        return nutrients?.map {
//            it.name + SEPARATOR + it.amount + SEPARATOR + it.unit
//        }.toString()
//    }

    @TypeConverter
    fun stringToNutrients(value: String): Nutrients {
        return Gson().fromJson(value, Nutrients::class.java)
    }

    @TypeConverter
    fun nutrientsToString(nutrients: Nutrients): String {
        return Gson().toJson(nutrients)
    }
}