package com.rafal.caloriecounter.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.rafal.caloriecounter.utilities.BMRCalculatorUtil

class CalculatorViewModel : ViewModel() {
    private val _bmrLiveData: MutableLiveData<Double> = MutableLiveData()
    val bmrLiveData: LiveData<Double> = _bmrLiveData

    fun calculateBMR(
        gender: Int,
        weight: Float,
        height: Float,
        age: Int,
        activity: Int,
        goal: Int
    ) {
        _bmrLiveData.value = BMRCalculatorUtil.calculateBMRForGoal(
            gender, weight, height, age, activity, goal
        )
    }
}