package com.aribhatt.automate;

import android.app.Activity;
import android.app.Instrumentation;
import android.support.test.rule.ActivityTestRule;
import android.view.View;

import com.aribhatt.automate.ui.PriorityContactsActivity;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.*;

public class MainActivityTest {

    @Rule
    public ActivityTestRule<MainActivity> mainActivityActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    private MainActivity mainActivity = null;

    Instrumentation.ActivityMonitor monitor = getInstrumentation().addMonitor(PriorityContactsActivity.class.getName(), null, false);

    @Before
    public void setUp() throws Exception {
        mainActivity = mainActivityActivityTestRule.getActivity();
    }

    @Test
    public void testLaunch(){
        View view1 = mainActivity.findViewById(R.id.music);
        assertNotNull(view1);
    }

    @Test
    public void testIntentFire(){
        View view1 = mainActivity.findViewById(R.id.phonebook);
        assertNotNull(view1);
        onView(withId(R.id.phonebook)).perform(click());
        Activity activity = getInstrumentation().waitForMonitorWithTimeout(monitor, 5000);
        assertNotNull(activity);
        activity.finish();
    }

    @After
    public void tearDown() throws Exception {
        mainActivity = null;
    }
}