package com.hkurbardovic.ottonova.main.fragments

import android.widget.TextView
import androidx.test.espresso.Espresso
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.rule.ActivityTestRule
import com.hkurbardovic.ottonova.R
import com.hkurbardovic.ottonova.main.MainActivity
import org.hamcrest.CoreMatchers
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class EventDetailsFragmentTest {

    private val title = "Title"

    @get:Rule
    var activityRule = ActivityTestRule(MainActivity::class.java)

    @Before
    fun init() {
        activityRule.activity.replaceFragment(EventsFragment.newInstance())
        activityRule.activity.addFragment(EventDetailsFragment.newInstance(title))
    }

    @Test
    fun basicViewsDisplayed() {
        Espresso.onView(
            CoreMatchers.allOf(
                CoreMatchers.instanceOf(TextView::class.java),
                ViewMatchers.withParent(ViewMatchers.withId(R.id.event_details_text_view))
            )
        )

        Espresso.onView(ViewMatchers.withId(R.id.event_details_text_view))
            .check(ViewAssertions.matches((ViewMatchers.isDisplayed())))
        Espresso.onView(ViewMatchers.withId(R.id.event_details_text_view))
            .check(ViewAssertions.matches(ViewMatchers.withText(title)))
    }
}