package com.ktt.archsample.assertion

import android.view.View
import androidx.test.espresso.NoMatchingViewException
import androidx.test.espresso.ViewAssertion
import androidx.test.espresso.matcher.ViewMatchers
import org.hamcrest.Matcher

/**
 * @author luke_kao
 */
abstract class RecyclerViewAssertion : ViewAssertion {

    override fun check(view: View, noViewFoundException: NoMatchingViewException?) {
        if (noViewFoundException != null) {
            throw noViewFoundException
        }

        val recyclerView = view as androidx.recyclerview.widget.RecyclerView
        assert(recyclerView)
    }

    abstract fun assert(recyclerView: androidx.recyclerview.widget.RecyclerView)
}

class RecyclerViewItemCountAssertion(private val itemCountMatcher: Matcher<Int>) : RecyclerViewAssertion() {
    override fun assert(recyclerView: androidx.recyclerview.widget.RecyclerView) {
        val adapter = recyclerView.adapter
        ViewMatchers.assertThat(adapter?.itemCount, itemCountMatcher)
    }
}