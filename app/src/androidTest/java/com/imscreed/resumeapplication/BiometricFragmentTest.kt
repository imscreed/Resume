package com.imscreed.resumeapplication


import android.view.View
import android.view.ViewGroup
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf
import org.hamcrest.TypeSafeMatcher
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class BiometricFragmentTest {

    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(RouteActivity::class.java)

    @Test
    fun verifyViewsExist() {
        val button = onView(
            allOf(
                withId(R.id.authenticateButton),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.container),
                        0
                    ),
                    2
                ),
                isDisplayed()
            )
        )
        button.check(matches(isDisplayed()))

        val textView = onView(
            allOf(
                withId(R.id.description),
                withText("You can view sensitive data only after authenticating your identity"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.container),
                        0
                    ),
                    1
                ),
                isDisplayed()
            )
        )
        textView.check(matches(withText("You can view sensitive data only after authenticating your identity")))

        val textView2 = onView(
            allOf(
                withId(R.id.title), withText("AUTHENTICATION REQUIRED"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.container),
                        0
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        textView2.check(matches(withText("AUTHENTICATION REQUIRED")))
    }

    private fun childAtPosition(
        parentMatcher: Matcher<View>, position: Int
    ): Matcher<View> {

        return object : TypeSafeMatcher<View>() {
            override fun describeTo(description: Description) {
                description.appendText("Child at position $position in parent ")
                parentMatcher.describeTo(description)
            }

            public override fun matchesSafely(view: View): Boolean {
                val parent = view.parent
                return parent is ViewGroup && parentMatcher.matches(parent)
                        && view == parent.getChildAt(position)
            }
        }
    }
}
