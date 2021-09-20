package com.mkodo.training.stevenw.movieapp

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.ViewAssertion
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityShould {

//    @get:Rule
//    var activityRule: ActivityScenarioRule<MainActivity>
//            = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun displayHelloFirstFragmentText() {
        onView(withText("Hello first fragme")).check(matches(isDisplayed()))
    }
}