package com.thescore.proficiencyexercise.screens.teamList

import android.os.SystemClock
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.thescore.proficiencyexercise.R
import com.thescore.proficiencyexercise.base.BaseUITest
import com.thescore.proficiencyexercise.di.generateTestAppComponent
import com.thescore.proficiencyexercise.helpers.recyclerItemAtPosition
import com.thescore.proficiencyexercise.ui.main.MainActivity
import com.thescore.proficiencyexercise.ui.memberlist.TeamListAdapter
import okhttp3.mockwebserver.MockWebServer
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.core.context.loadKoinModules
import org.koin.test.inject
import java.net.HttpURLConnection

@RunWith(AndroidJUnit4::class)
class TeamListTest : BaseUITest(){

    @Rule
    @JvmField
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(MainActivity::class.java, true, false)

    //Inject Mockwebserver created with koin
    val  mMockWebServer : MockWebServer by inject()

    val mNameTestOne = "Atlanta Hawks"
    val mWinsOne = 20
    val mNameTestTwo = "Washington Wizards"
    val mWinsTwo = 36

    @Before
    fun start(){
        super.setUp()
        loadKoinModules(generateTestAppComponent(getMockWebServerUrl()).toMutableList())
    }

    @Test
    fun test_recyclerview_elements_for_expected_response() {
        mActivityTestRule.launchActivity(null)

        mockNetworkResponseWithFileContent("success_resp.json", HttpURLConnection.HTTP_OK)

        //Wait for MockWebServer to get back with response
        SystemClock.sleep(1000)

        //Check if item at 0th position is having 0th element in json
        onView(withId(R.id.rvItems))
            .check(
                matches(
                    recyclerItemAtPosition(
                        0,
                        ViewMatchers.hasDescendant(withText(mNameTestOne))
                    )))

        onView(withId(R.id.rvItems))
            .check(
                matches(
                    recyclerItemAtPosition(
                        0,
                        ViewMatchers.hasDescendant(withText(mWinsOne))
                    )))

        //Scroll to last index in json
        onView(withId(R.id.rvItems)).perform(
            RecyclerViewActions.scrollToPosition<TeamListAdapter.TeamViewHolder>(9))

        //Check if item at last position is having 9th element in json
        onView(withId(R.id.rvItems))
            .check(matches(recyclerItemAtPosition(29, ViewMatchers.hasDescendant(withText(mNameTestTwo)))))

        onView(withId(R.id.rvItems))
            .check(matches(recyclerItemAtPosition(29, ViewMatchers.hasDescendant(withText(mWinsTwo)))))

    }
}