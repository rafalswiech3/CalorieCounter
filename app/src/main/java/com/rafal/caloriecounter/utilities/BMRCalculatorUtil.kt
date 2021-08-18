package com.rafal.caloriecounter.utilities

import androidx.annotation.VisibleForTesting

object BMRCalculatorUtil {
    private val harrisBenedictFormula = listOf(1.2, 1.375, 1.55, 1.725, 1.9)
    private val goalFormula = listOf(-300, 0, 300)

    fun validateGender(input: Int): Boolean = input > 0

    fun validateWeight(input: String): Boolean = input.isNotEmpty()

    fun validateHeight(input: String): Boolean = input.isNotEmpty()

    fun validateAge(input: String): Boolean = input.isNotEmpty()

    fun validateActivity(input: Int): Boolean = input > 0

    fun validateGoal(input: Int): Boolean = input > 0

    fun calculateBMRForGoal(
        gender: Int,
        weight: Float,
        height: Float,
        age: Int,
        activity: Int,
        goal: Int
    ): Double {
        val bmr = calculateBMR(gender, weight, height, age)
        val eat = calculateEAT(bmr, activity)
        return eat + goalFormula[goal - 1]
    }

    fun calculateBMR(
        gender: Int,
        weight: Float,
        height: Float,
        age: Int
    ) : Double {
        val genderVar = if(gender == 1) 5 else -161
        return 9.99f * weight + 6.25f * height - 4.92 * age + genderVar
    }

    fun calculateEAT(
        bmr: Double,
        activity: Int
    ) : Double {
        return bmr * harrisBenedictFormula[activity - 1]
    }
}