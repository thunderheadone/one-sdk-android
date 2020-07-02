package com.thunderhead.dynamicconfigurationexample

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.intent.rule.IntentsTestRule
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Test

import org.junit.Rule
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityTest {
    @get:Rule
    var intentsRule: IntentsTestRule<MainActivity> = IntentsTestRule(MainActivity::class.java)

    @Test
    fun dynamicRegionSelection() {
        onView(withId(R.id.selectButton))
            .check(matches(isDisplayed()))
            .perform(click())

        onView(withText("United States"))
            .check(matches(isDisplayed()))

        onView(withText("United Kingdom"))
            .check(matches(isDisplayed()))

        onView(withText("Europe"))
            .check(matches(isDisplayed()))
            .perform(click())

        onView(withId(R.id.regionTextView))
            .check(matches(isDisplayed()))
    }
}