package com.rafal.caloriecounter.fragments


import android.content.Context
import androidx.navigation.NavController
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.replaceText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.filters.MediumTest
import com.rafal.caloriecounter.R
import com.rafal.caloriecounter.utilities.BMRCalculatorUtil
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import launchFragmentInHiltContainer
import org.junit.Rule
import org.junit.Test
import org.mockito.AdditionalMatchers.not
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify

@MediumTest
@HiltAndroidTest
class CalculatorFragmentTest {
    private val context = ApplicationProvider.getApplicationContext<Context>()

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Test
    fun clickCalculateButton_invalidInput_displayErrorsOnFormViews() {
        launchFragmentInHiltContainer<CalculatorFragment>()

        onView(withId(R.id.calc_calculate_btn)).perform(click())

        onView(withId(R.id.calc_weight_in)).check(matches(hasErrorText(context.getString(R.string.item_required))))
        onView(withId(R.id.calc_height_in)).check(matches(hasErrorText(context.getString(R.string.item_required))))
        onView(withId(R.id.calc_age_in)).check(matches(hasErrorText(context.getString(R.string.item_required))))
    }

    @Test
    fun clickCalculateButton_correctInput_setTextViewsAndApplyButtonVisible() {
        launchFragmentInHiltContainer<CalculatorFragment>()

        onView(withId(R.id.calc_weight_in)).perform(replaceText("50"))
        onView(withId(R.id.calc_height_in)).perform(replaceText("150"))
        onView(withId(R.id.calc_age_in)).perform(replaceText("25"))

        onView(withId(R.id.calc_gender_spinner)).perform(click())
        onView(withText(context.resources.getStringArray(R.array.genders)[1])).perform(click());

        onView(withId(R.id.calc_activity_spinner)).perform(click())
        onView(withText(context.resources.getStringArray(R.array.activity_level)[1])).perform(click());

        onView(withId(R.id.calc_goal_spinner)).perform(click())
        onView(withText(context.resources.getStringArray(R.array.goals)[1])).perform(click());

        onView(withId(R.id.calc_calculate_btn)).perform(click())

        onView(withId(R.id.calc_your_bmr_tv)).check(matches(isDisplayed()))
        onView(withId(R.id.calc_calculated_bmr_tv)).check(matches(isDisplayed()))
        onView(withId(R.id.calc_apply_button)).check(matches(isDisplayed()))
    }
}