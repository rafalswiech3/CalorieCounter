package com.rafal.caloriecounter.data

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.util.*

@Entity
data class IngredientSearch (
    @PrimaryKey(autoGenerate = true) val key: Int,
    var date: String,
    val id: Int,
    var meal: Int,
    val name: String,
    val image: String,
    var amount: Float,
    val unit: String,
    @SerializedName("nutrition") val nutrients: Nutrients
)