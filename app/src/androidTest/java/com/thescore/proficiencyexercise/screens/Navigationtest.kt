package com.thescore.proficiencyexercise.screens
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.*
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.*
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.thescore.proficiencyexercise.R
import com.thescore.proficiencyexercise.ui.main.MainActivity
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4ClassRunner::class)
class NavigationTest{


    @Test
    fun testFragmentsNavigation() {

        // SETUP
        val activityScenario = ActivityScenario.launch(MainActivity::class.java)

        // NAV
        onView(withId(R.id.cardRow)).perform(click())

        // VERIFY
        onView(withId(R.id.fragment_team_detail_list_container))
            .check(matches(isDisplayed()))

        // VERIFY
        onView(withId(R.id.firstName))
            .check(matches(isDisplayed()))

        // NAV
        pressBack()

        // VERIFY
        onView(withId(R.id.fragment_team_list_container))
            .check(matches(isDisplayed()))

        // NAV
        pressBack()
    }

}
