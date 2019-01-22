package com.example.user.weather.activity;


import android.support.test.espresso.DataInteraction;
import android.support.test.espresso.ViewInteraction;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import com.example.user.weather.R;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu;
import static android.support.test.espresso.Espresso.pressBack;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withClassName;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.anything;
import static org.hamcrest.Matchers.is;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class SearchPlaceActivityTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void searchPlaceActivityTest() throws Exception{
            Thread.sleep(1000);

        openActionBarOverflowOrOptionsMenu(getInstrumentation().getTargetContext());

        ViewInteraction appCompatTextView = onView(allOf(withId(R.id.title), withText("Searching city"), isDisplayed()));
        appCompatTextView.perform(click());

            Thread.sleep(1000);

        ViewInteraction appCompatEditText = onView(allOf(withId(R.id.textLine), isDisplayed()));
        appCompatEditText.perform(click());

        ViewInteraction appCompatEditText2 = onView(allOf(withId(R.id.textLine), isDisplayed()));
        appCompatEditText2.perform(replaceText("Rome"), closeSoftKeyboard());

        ViewInteraction appCompatButton = onView(allOf(withId(R.id.searchButton), withText("SEARCH"), isDisplayed()));
        appCompatButton.perform(click());

        DataInteraction textView = onData(anything())
                .inAdapterView(allOf(withId(R.id.list_town), childAtPosition(
                        withClassName(is("android.widget.LinearLayout")),
                                2)))
                .atPosition(0);
        textView.perform(click());

            Thread.sleep(1000);

        ViewInteraction appCompatButton2 = onView(allOf(withId(R.id.button), withText("Next 5 days"), isDisplayed()));
        appCompatButton2.perform(click());

            Thread.sleep(1000);

        pressBack();
    }

    private static Matcher<View> childAtPosition(
            final Matcher<View> parentMatcher, final int position) {

        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                        && view.equals(((ViewGroup) parent).getChildAt(position));
            }
        };
    }
}
