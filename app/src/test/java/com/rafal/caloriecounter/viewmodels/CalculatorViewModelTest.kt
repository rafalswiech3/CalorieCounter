package com.rafal.caloriecounter.viewmodels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth.assertThat
import getOrAwaitValue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule

class CalculatorViewModelTest {
    private lateinit var viewModel: CalculatorViewModel

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setup() {
        viewModel = CalculatorViewModel()
    }

    @Test
    fun `calculate bmr returns live data`() {
        viewModel.calculateBMR(1, 50.0f, 150.0f, 25, 1, 1)
        val value = viewModel.bmrLiveData.getOrAwaitValue()
        assertThat(value).isNotNull()
    }
}