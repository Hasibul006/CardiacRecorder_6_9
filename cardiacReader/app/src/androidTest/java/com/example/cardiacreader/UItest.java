package com.example.cardiacreader;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.*;

import androidx.test.espresso.action.ViewActions;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)

public class UItest {

    @Rule
    public ActivityScenarioRule<Signin> activityRule = new ActivityScenarioRule<>(Signin.class);

    @Test
    public void loginTest()
    {

        onView(withId(R.id.signinlayout)).check(matches(isDisplayed()));
        onView(withId(R.id.user)).perform(ViewActions.typeText("hemal9730@gmail.com"));
        onView(withId(R.id.pass)).perform(ViewActions.typeText("1234567"));
        onView(withId(R.id.login)).perform(click());
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        onView(withId(R.id.recyclerView)).check(matches(isDisplayed()));
    }

}