package com.quaksire.simpleappdemo

import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Rule
import android.content.Intent
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.isDisplayed
import android.support.test.espresso.matcher.ViewMatchers.withText
import org.hamcrest.Matchers.allOf


/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class UserScenarios: BaseScenario() {
    @get:Rule
    val mActivityTestRule: ActivityTestRule<ListPostActivity> = ActivityTestRule(ListPostActivity::class.java, true, false)

    @Test
    fun canViewListOfPost() {
        setResponse200ToServer(POSTS, 200)

        val intent = Intent()
        mActivityTestRule.launchActivity(intent)

        onView(allOf(
            withText("post title"),
            isDisplayed())).check(matches(withText("post title")))
    }

    @Test
    fun canViewErrorMessage() {
        val intent = Intent()
        mActivityTestRule.launchActivity(intent)

        onView(allOf(
            withText(R.string.list_loading),
            isDisplayed())).check(matches(withText(R.string.list_loading)))

        //TODO - Replace with IdleResources
        Thread.sleep(5000)

        onView(allOf(
            withText(R.string.list_error),
            isDisplayed())).check(matches(withText(R.string.list_error)))
    }

    @Test
    fun canNavigateToPostActivity() {
        setResponse200ToServer(POSTS, 200)

        val intent = Intent()
        mActivityTestRule.launchActivity(intent)

        onView(allOf(
            withText("post title"),
            isDisplayed())).check(matches(withText("post title")))

        onView(withText("post title")).perform(click())
    }

    @Test
    fun canViewPostActivity() {
        setResponse200ToServer(POSTS, 200)
        setResponse200ToServer(POST, 200)
        setResponse200ToServer(USER, 200)
        setResponse200ToServer(COMMENTS, 200)

        val intent = Intent()
        mActivityTestRule.launchActivity(intent)

        onView(allOf(
            withText("post title"),
            isDisplayed())).check(matches(withText("post title")))

        onView(withText("post title")).perform(click())

        onView(withText("post title")).check(matches(isDisplayed()))
        onView(withText("post body")).check(matches(isDisplayed()))
        onView(withText("user name")).check(matches(isDisplayed()))
        onView(withText("1")).check(matches(isDisplayed()))
    }
}
