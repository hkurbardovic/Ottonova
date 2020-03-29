package com.hkurbardovic.ottonova.main.fragments

import android.view.View
import android.widget.ExpandableListView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.rule.ActivityTestRule
import com.hkurbardovic.ottonova.R
import com.hkurbardovic.ottonova.main.MainActivity
import org.hamcrest.CoreMatchers
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf
import org.junit.Before
import org.junit.Rule
import org.junit.Test


class EventsFragmentTest {

    private val changedText = "changed text"

    @get:Rule
    var activityRule = ActivityTestRule(MainActivity::class.java)

    @Before
    fun init() {
        activityRule.activity.replaceFragment(EventsFragment.newInstance())
    }

    @Test
    fun basicViewsDisplayed() {
        onView(
            CoreMatchers.allOf(
                CoreMatchers.instanceOf(TextView::class.java),
                withParent(withId(R.id.events_text_view))
            )
        )
        onView(
            CoreMatchers.allOf(
                CoreMatchers.instanceOf(CardView::class.java),
                withParent(withId(R.id.card_view))
            )
        )
        onView(
            CoreMatchers.allOf(
                CoreMatchers.instanceOf(ExpandableListView::class.java),
                withParent(withId(R.id.expandable_list_view))
            )
        )

        onView(withId(R.id.events_text_view)).check(matches((isDisplayed())))
        onView(withId(R.id.events_text_view)).check(matches(withText(R.string.events)))

        onView(withId(R.id.expandable_list_view)).check(matches((isDisplayed())))

        onView(withId(R.id.events_text_view)).perform(setTextInTextView())
        onView(withId(R.id.events_text_view)).check(matches(withText(changedText)))
    }

    private fun setTextInTextView(): ViewAction? {
        return object : ViewAction {
            override fun getConstraints(): Matcher<View> {
                return allOf(isDisplayed(), isAssignableFrom(TextView::class.java))
            }

            override fun perform(uiController: UiController?, view: View) {
                (view as TextView).text = changedText
            }

            override fun getDescription(): String {
                return "replace text"
            }
        }
    }
}