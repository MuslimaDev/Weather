package com.example.user.weather.activity;


import android.support.test.espresso.ViewInteraction;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.example.user.weather.R;

import junit.framework.Assert;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu;
import static android.support.test.espresso.Espresso.pressBack;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void mainActivityTest() throws Exception {
        Thread.sleep(1000);

        ViewInteraction appCompatButton = onView(allOf(withId(R.id.button), withText("Next 5 days"), isDisplayed()));
        appCompatButton.perform(click());

        Thread.sleep(1000);

        pressBack();

        Thread.sleep(1000);

        openActionBarOverflowOrOptionsMenu(getInstrumentation().getTargetContext());
    }

    @Test
    public void isNotNull(){
        Assert.assertNotNull(R.id.currentlocation);
        Assert.assertNotNull(R.id.temperature);
        Assert.assertNotNull(R.id.feelsLike);
        Assert.assertNotNull(R.id.wind);
        Assert.assertNotNull(R.id.visibility);
    }
}
