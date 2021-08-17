package com.rafal.caloriecounter.fragments

import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.filters.MediumTest
import com.rafal.caloriecounter.R
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import launchFragmentInHiltContainer
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify

@MediumTest
@HiltAndroidTest
class WelcomeFragmentTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Test
    fun clickOkButton_navigateToCalculatorFragment() {
        val navController = mock(NavController::class.java)

        launchFragmentInHiltContainer<WelcomeFragment> {
            Navigation.setViewNavController(requireView(), navController)
        }

        onView(withId(R.id.welcome_next_btn)).perform(click())

        verify(navController).navigate(
            WelcomeFragmentDirections.actionWelcomeFragmentToCalculatorFragment()
        )
    }
}