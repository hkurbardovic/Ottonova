package com.hkurbardovic.ottonova.main

import android.widget.FrameLayout
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withParent
import androidx.test.rule.ActivityTestRule
import com.hkurbardovic.ottonova.R
import org.hamcrest.CoreMatchers.allOf
import org.hamcrest.CoreMatchers.instanceOf
import org.junit.Rule
import org.junit.Test

class MainActivityTest {
    @get:Rule
    var activityRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun basicViewsDisplayed() {
        onView(withId(R.id.fragment_container)).check(ViewAssertions.matches((ViewMatchers.isDisplayed())))

        onView(
            allOf(
                instanceOf(FrameLayout::class.java),
                withParent(withId(R.id.fragment_container))
            )
        )
        onView(
            allOf(
                instanceOf(CoordinatorLayout::class.java),
                withParent(withId(R.id.coordinator))
            )
        )
    }
}