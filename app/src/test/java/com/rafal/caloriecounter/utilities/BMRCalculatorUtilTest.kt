package com.rafal.caloriecounter.utilities

import androidx.test.filters.SmallTest
import com.google.common.truth.Truth.assertThat
import org.junit.Test

@SmallTest
class BMRCalculatorUtilTest {
    @Test
    fun `empty gender returns false`() {
        val result = BMRCalculatorUtil.validateGender(0)
        assertThat(result).isFalse()
    }

    @Test
    fun `correct gender returns true`() {
        val result = BMRCalculatorUtil.validateGender(1)
        assertThat(result).isTrue()
    }

    @Test
    fun `empty weight returns false`() {
        val result = BMRCalculatorUtil.validateWeight("")
        assertThat(result).isFalse()
    }

    @Test
    fun `correct weight returns true`() {
        val result = BMRCalculatorUtil.validateWeight("50")
        assertThat(result).isTrue()
    }

    @Test
    fun `empty height returns false`() {
        val result = BMRCalculatorUtil.validateHeight("")
        assertThat(result).isFalse()
    }

    @Test
    fun `correct height returns true`() {
        val result = BMRCalculatorUtil.validateHeight("50")
        assertThat(result).isTrue()
    }

    @Test
    fun `empty age returns false`() {
        val result = BMRCalculatorUtil.validateAge("")
        assertThat(result).isFalse()
    }

    @Test
    fun `correct age returns true`() {
        val result = BMRCalculatorUtil.validateAge("50")
        assertThat(result).isTrue()
    }

    @Test
    fun `empty activity returns false`() {
        val result = BMRCalculatorUtil.validateActivity(0)
        assertThat(result).isFalse()
    }

    @Test
    fun `correct activity returns true`() {
        val result = BMRCalculatorUtil.validateActivity(1)
        assertThat(result).isTrue()
    }

    @Test
    fun `empty goal returns false`() {
        val result = BMRCalculatorUtil.validateGoal(0)
        assertThat(result).isFalse()
    }

    @Test
    fun `correct goal returns true`() {
        val result = BMRCalculatorUtil.validateGoal(1)
        assertThat(result).isTrue()
    }

    @Test
    fun `female BMR returns correct value`() {
        val result = BMRCalculatorUtil.calculateBMR(
            gender = 2,
            weight = 50.0f,
            height = 150.0f,
            age = 25
        )
        assertThat(result).isEqualTo(1153)
    }

    @Test
    fun `male BMR returns correct value`() {
        val result = BMRCalculatorUtil.calculateBMR(
            gender = 1,
            weight = 50.0f,
            height = 150.0f,
            age = 25
        )
        assertThat(result).isEqualTo(1319)
    }

    @Test
    fun `eat no exercise returns correct value`() {
        val result = BMRCalculatorUtil.calculateEAT(1500.0, 1)
        assertThat(result).isEqualTo(1800)
    }

    @Test
    fun `eat light exercise returns correct value`() {
        val result = BMRCalculatorUtil.calculateEAT(1500.0, 2)
        assertThat(result).isEqualTo(2062.5)
    }

    @Test
    fun `eat moderate exercise returns correct value`() {
        val result = BMRCalculatorUtil.calculateEAT(1500.0, 3)
        assertThat(result).isEqualTo(2325)
    }

    @Test
    fun `eat very active returns correct value`() {
        val result = BMRCalculatorUtil.calculateEAT(1500.0, 4)
        assertThat(result).isEqualTo(2587.5)
    }

    @Test
    fun `eat extra active returns correct value`() {
        val result = BMRCalculatorUtil.calculateEAT(1500.0, 5)
        assertThat(result).isEqualTo(2850)
    }

    @Test
    fun `bmr for goal loose weight returns correct value`() {
        val result = BMRCalculatorUtil.calculateBMRForGoal(
            gender = 1,
            weight = 50.0f,
            height = 150.0f,
            age = 25,
            activity = 1,
            goal = 1
        )
        assertThat(result).isEqualTo(1282.8)
    }

    @Test
    fun `bmr for goal keep weight returns correct value`() {
        val result = BMRCalculatorUtil.calculateBMRForGoal(
            gender = 1,
            weight = 50.0f,
            height = 150.0f,
            age = 25,
            activity = 1,
            goal = 2
        )
        assertThat(result).isEqualTo(1582.8)
    }

    @Test
    fun `bmr for goal gain weight returns correct value`() {
        val result = BMRCalculatorUtil.calculateBMRForGoal(
            gender = 1,
            weight = 50.0f,
            height = 150.0f,
            age = 25,
            activity = 1,
            goal = 3
        )
        assertThat(result).isEqualTo(1882.8)
    }

}