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

public class addTest {

    @Rule
    public ActivityScenarioRule<addRecords> activityRule = new ActivityScenarioRule<>(addRecords.class);
    public ActivityScenarioRule<MainActivity> activityRule2 = new ActivityScenarioRule<>(MainActivity.class);


    @Test
    public void addTest()
    {
        onView(withId(R.id.addinglayout)).check(matches(isDisplayed()));
        onView(withId(R.id.etSystolic)).perform(ViewActions.typeText("78"));
        onView(withId(R.id.etDiastolic)).perform(ViewActions.typeText("115"));
        onView(withId(R.id.etHeartRate)).perform(ViewActions.typeText("80"));
        onView(withId(R.id.etComment)).perform(ViewActions.typeText("Good"));

        onView(withId(R.id.btnSave)).perform(click());
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}