package com.ktt.archsample.activity

import android.content.Intent
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import com.ktt.archsample.R
import com.ktt.archsample.assertion.RecyclerViewItemCountAssertion
import org.hamcrest.Matchers.greaterThan
import org.hamcrest.Matchers.not
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


/**
 * @author luke_kao
 */
@RunWith(AndroidJUnit4::class)
class MainActivityTest {
    @Rule @JvmField
    val activityRule = ActivityTestRule(MainActivity::class.java)

    @Before
    fun setUp() {
        activityRule.launchActivity(Intent())
    }

    @Test
    fun dice() {
        onView(withId(R.id.textView)).check(matches(withText("")))

        onView(withId(R.id.button))
                .check(matches(isDisplayed()))
                .perform(click())

        onView(withId(R.id.progressBar)).check(matches(isDisplayed()))
        onView(withId(R.id.textView)).check(matches(not(withText(""))))
        onView(withId(R.id.recyclerView)).check(RecyclerViewItemCountAssertion(greaterThan(0)))
    }
}