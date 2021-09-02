package com.rafal.caloriecounter.data

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class IngredientSearch (
    @PrimaryKey(autoGenerate = true) val key: Int,
    val id: Int,
    var meal: Int,
    val name: String,
    val image: String,
    val amount: Float,
    @SerializedName("nutrition") val nutrients: Nutrients
)