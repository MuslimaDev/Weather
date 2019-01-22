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

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.pressBack;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class FiveDaysForecastTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void fiveDaysForecastTest() throws Exception {

        Thread.sleep(1000);

        ViewInteraction appCompatButton = onView(allOf(withId(R.id.button), withText("Next 5 days"), isDisplayed()));
        appCompatButton.perform(click());

        Thread.sleep(1000);

        pressBack();
    }
    @Test
    public void isNotNull(){
        Assert.assertNotNull(R.id.date);
        Assert.assertNotNull(R.id.minimumTemp);
        Assert.assertNotNull(R.id.maximumTemp);
    }
}
