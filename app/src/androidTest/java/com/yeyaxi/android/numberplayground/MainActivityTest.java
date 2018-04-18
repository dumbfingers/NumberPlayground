package com.yeyaxi.android.numberplayground;

import android.arch.core.executor.testing.InstantTaskExecutorRule;
import android.support.test.espresso.action.ViewActions;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
public class MainActivityTest {

    @Rule
    public InstantTaskExecutorRule instantExecutor = new InstantTaskExecutorRule();

    @Rule
    public ActivityTestRule<MainActivity> activityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void valueChangedTest() {
        onView(withId(R.id.editText1)).perform(ViewActions.typeText("1"));
        onView(withId(R.id.editText2)).perform(ViewActions.typeText("2"));
        onView(withId(R.id.editText3)).perform(ViewActions.typeText("3"));
        onView(withId(R.id.editText4)).perform(ViewActions.typeText("4.5"));
        onView(withId(R.id.editText5)).perform(ViewActions.typeText("5"));
        onView(withId(R.id.editText6)).perform(ViewActions.typeText("6.5"));
    }

    @Test
    public void sumValueTest() {
        valueChangedTest();
        onView(withId(R.id.textView)).check(matches(withText("22")));
    }

}
