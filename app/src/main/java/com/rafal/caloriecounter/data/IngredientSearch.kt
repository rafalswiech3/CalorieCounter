package com.rafal.caloriecounter.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class IngredientSearch (
    @PrimaryKey(autoGenerate = true) val key: Int,
    val id: Int,
    var meal: Int,
    val name: String,
    val image: String
)