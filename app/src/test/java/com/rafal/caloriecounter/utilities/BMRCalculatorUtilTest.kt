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

    // region female tests
    @Test
    fun `calculate bmr female, little exercise, loose weight`() {
        val result = BMRCalculatorUtil.calculateBMR(
            gender = 2,
            weight = 50f,
            height = 165f,
            age = 25,
            activity = 1,
            goal = 1
        )
        assertThat(result).isEqualTo(1319)
    }

    @Test
    fun `calculate bmr female, little exercise, keep weight`() {
        val result = BMRCalculatorUtil.calculateBMR(
            gender = 2,
            weight = 50f,
            height = 165f,
            age = 25,
            activity = 1,
            goal = 2
        )
        assertThat(result).isEqualTo(1619)
    }

    @Test
    fun `calculate bmr female, little exercise, gain weight`() {
        val result = BMRCalculatorUtil.calculateBMR(
            gender = 2,
            weight = 50f,
            height = 165f,
            age = 25,
            activity = 1,
            goal = 3
        )
        assertThat(result).isEqualTo(1919)
    }

    @Test
    fun `calculate bmr female, light exercise, loose weight`() {
        val result = BMRCalculatorUtil.calculateBMR(
            gender = 2,
            weight = 50f,
            height = 165f,
            age = 25,
            activity = 2,
            goal = 1
        )
        assertThat(result).isEqualTo(1444)
    }

    @Test
    fun `calculate bmr female, light exercise, keep weight`() {
        val result = BMRCalculatorUtil.calculateBMR(
            gender = 2,
            weight = 50f,
            height = 165f,
            age = 25,
            activity = 2,
            goal = 2
        )
        assertThat(result).isEqualTo(1744)
    }

    @Test
    fun `calculate bmr female, light exercise, gain weight`() {
        val result = BMRCalculatorUtil.calculateBMR(
            gender = 2,
            weight = 50f,
            height = 165f,
            age = 25,
            activity = 2,
            goal = 3
        )
        assertThat(result).isEqualTo(2044)
    }

    @Test
    fun `calculate bmr female, moderate exercise, loose weight`() {
        val result = BMRCalculatorUtil.calculateBMR(
            gender = 2,
            weight = 50f,
            height = 165f,
            age = 25,
            activity = 3,
            goal = 1
        )
        assertThat(result).isEqualTo(1568)
    }

    @Test
    fun `calculate bmr female, moderate exercise, keep weight`() {
        val result = BMRCalculatorUtil.calculateBMR(
            gender = 2,
            weight = 50f,
            height = 165f,
            age = 25,
            activity = 3,
            goal = 2
        )
        assertThat(result).isEqualTo(1868)
    }

    @Test
    fun `calculate bmr female, moderate exercise, gain weight`() {
        val result = BMRCalculatorUtil.calculateBMR(
            gender = 2,
            weight = 50f,
            height = 165f,
            age = 25,
            activity = 3,
            goal = 3
        )
        assertThat(result).isEqualTo(2168)
    }

    @Test
    fun `calculate bmr female, very active, loose weight`() {
        val result = BMRCalculatorUtil.calculateBMR(
            gender = 2,
            weight = 50f,
            height = 165f,
            age = 25,
            activity = 4,
            goal = 1
        )
        assertThat(result).isEqualTo(1693)
    }

    @Test
    fun `calculate bmr female, very active, keep weight`() {
        val result = BMRCalculatorUtil.calculateBMR(
            gender = 2,
            weight = 50f,
            height = 165f,
            age = 25,
            activity = 4,
            goal = 2
        )
        assertThat(result).isEqualTo(1993)
    }

    @Test
    fun `calculate bmr female, very active, gain weight`() {
        val result = BMRCalculatorUtil.calculateBMR(
            gender = 2,
            weight = 50f,
            height = 165f,
            age = 25,
            activity = 4,
            goal = 3
        )
        assertThat(result).isEqualTo(2293)
    }

    @Test
    fun `calculate bmr female, extra active, loose weight`() {
        val result = BMRCalculatorUtil.calculateBMR(
            gender = 2,
            weight = 50f,
            height = 165f,
            age = 25,
            activity = 5,
            goal = 1
        )
        assertThat(result).isEqualTo(1817)
    }

    @Test
    fun `calculate bmr female, extra active, keep weight`() {
        val result = BMRCalculatorUtil.calculateBMR(
            gender = 2,
            weight = 50f,
            height = 165f,
            age = 25,
            activity = 5,
            goal = 2
        )
        assertThat(result).isEqualTo(2117)
    }

    @Test
    fun `calculate bmr female, extra active, gain weight`() {
        val result = BMRCalculatorUtil.calculateBMR(
            gender = 2,
            weight = 50f,
            height = 165f,
            age = 25,
            activity = 5,
            goal = 3
        )
        assertThat(result).isEqualTo(2417)
    }
    // endregion female tests


    // region male tests
    @Test
    fun `calculate bmr male, little exercise, loose weight`() {
        val result = BMRCalculatorUtil.calculateBMR(
            gender = 1,
            weight = 50f,
            height = 165f,
            age = 25,
            activity = 1,
            goal = 1
        )
        assertThat(result).isEqualTo(1535)
    }

    @Test
    fun `calculate bmr male, little exercise, keep weight`() {
        val result = BMRCalculatorUtil.calculateBMR(
            gender = 1,
            weight = 50f,
            height = 165f,
            age = 25,
            activity = 1,
            goal = 2
        )
        assertThat(result).isEqualTo(1835)
    }

    @Test
    fun `calculate bmr male, little exercise, gain weight`() {
        val result = BMRCalculatorUtil.calculateBMR(
            gender = 1,
            weight = 50f,
            height = 165f,
            age = 25,
            activity = 1,
            goal = 3
        )
        assertThat(result).isEqualTo(2135)
    }

    @Test
    fun `calculate bmr male, light exercise, loose weight`() {
        val result = BMRCalculatorUtil.calculateBMR(
            gender = 1,
            weight = 50f,
            height = 165f,
            age = 25,
            activity = 2,
            goal = 1
        )
        assertThat(result).isEqualTo(1676)
    }

    @Test
    fun `calculate bmr male, light exercise, keep weight`() {
        val result = BMRCalculatorUtil.calculateBMR(
            gender = 1,
            weight = 50f,
            height = 165f,
            age = 25,
            activity = 2,
            goal = 2
        )
        assertThat(result).isEqualTo(1976)
    }

    @Test
    fun `calculate bmr male, light exercise, gain weight`() {
        val result = BMRCalculatorUtil.calculateBMR(
            gender = 1,
            weight = 50f,
            height = 165f,
            age = 25,
            activity = 2,
            goal = 3
        )
        assertThat(result).isEqualTo(2276)
    }

    @Test
    fun `calculate bmr male, moderate exercise, loose weight`() {
        val result = BMRCalculatorUtil.calculateBMR(
            gender = 1,
            weight = 50f,
            height = 165f,
            age = 25,
            activity = 3,
            goal = 1
        )
        assertThat(result).isEqualTo(1817)
    }

    @Test
    fun `calculate bmr male, moderate exercise, keep weight`() {
        val result = BMRCalculatorUtil.calculateBMR(
            gender = 1,
            weight = 50f,
            height = 165f,
            age = 25,
            activity = 3,
            goal = 2
        )
        assertThat(result).isEqualTo(2187)
    }

    @Test
    fun `calculate bmr male, moderate exercise, gain weight`() {
        val result = BMRCalculatorUtil.calculateBMR(
            gender = 1,
            weight = 50f,
            height = 165f,
            age = 25,
            activity = 3,
            goal = 3
        )
        assertThat(result).isEqualTo(2487)
    }

    @Test
    fun `calculate bmr male, very active, loose weight`() {
        val result = BMRCalculatorUtil.calculateBMR(
            gender = 1,
            weight = 50f,
            height = 165f,
            age = 25,
            activity = 4,
            goal = 1
        )
        assertThat(result).isEqualTo(1958)
    }

    @Test
    fun `calculate bmr male, very active, keep weight`() {
        val result = BMRCalculatorUtil.calculateBMR(
            gender = 1,
            weight = 50f,
            height = 165f,
            age = 25,
            activity = 4,
            goal = 2
        )
        assertThat(result).isEqualTo(2258)
    }

    @Test
    fun `calculate bmr male, very active, gain weight`() {
        val result = BMRCalculatorUtil.calculateBMR(
            gender = 1,
            weight = 50f,
            height = 165f,
            age = 25,
            activity = 4,
            goal = 3
        )
        assertThat(result).isEqualTo(2558)
    }

    @Test
    fun `calculate bmr male, extra active, loose weight`() {
        val result = BMRCalculatorUtil.calculateBMR(
            gender = 1,
            weight = 50f,
            height = 165f,
            age = 25,
            activity = 5,
            goal = 1
        )
        assertThat(result).isEqualTo(2100)
    }

    @Test
    fun `calculate bmr male, extra active, keep weight`() {
        val result = BMRCalculatorUtil.calculateBMR(
            gender = 1,
            weight = 50f,
            height = 165f,
            age = 25,
            activity = 5,
            goal = 2
        )
        assertThat(result).isEqualTo(2400)
    }

    @Test
    fun `calculate bmr male, extra active, gain weight`() {
        val result = BMRCalculatorUtil.calculateBMR(
            gender = 1,
            weight = 50f,
            height = 165f,
            age = 25,
            activity = 5,
            goal = 3
        )
        assertThat(result).isEqualTo(2700)
    }
    
    // endregion male test

}