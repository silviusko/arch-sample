package com.ktt.archsample.activity

import android.content.Intent
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import com.ktt.archsample.R
import com.ktt.archsample.assertion.RecyclerViewItemCountAssertion
import org.hamcrest.Matchers.greaterThan
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
        onView(withId(R.id.recyclerView)).check(RecyclerViewItemCountAssertion(greaterThan(0)))
    }
}