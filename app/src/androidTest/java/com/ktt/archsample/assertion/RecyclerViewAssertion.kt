package com.ktt.archsample.assertion

import android.support.test.espresso.NoMatchingViewException
import android.support.test.espresso.ViewAssertion
import android.support.test.espresso.matcher.ViewMatchers
import android.support.v7.widget.RecyclerView
import android.view.View
import org.hamcrest.Matcher

/**
 * @author luke_kao
 */
abstract class RecyclerViewAssertion : ViewAssertion {

    override fun check(view: View, noViewFoundException: NoMatchingViewException?) {
        if (noViewFoundException != null) {
            throw noViewFoundException
        }

        val recyclerView = view as RecyclerView
        assert(recyclerView)
    }

    abstract fun assert(recyclerView: RecyclerView)
}

class RecyclerViewItemCountAssertion(private val itemCountMatcher: Matcher<Int>) : RecyclerViewAssertion() {
    override fun assert(recyclerView: RecyclerView) {
        val adapter = recyclerView.adapter
        ViewMatchers.assertThat(adapter.itemCount, itemCountMatcher)
    }
}