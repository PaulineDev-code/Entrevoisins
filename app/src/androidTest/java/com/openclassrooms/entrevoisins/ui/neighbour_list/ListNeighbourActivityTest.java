package com.openclassrooms.entrevoisins.ui.neighbour_list;


import android.support.test.espresso.ViewInteraction;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import com.openclassrooms.entrevoisins.R;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.hamcrest.core.IsInstanceOf;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.swipeLeft;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withContentDescription;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withParent;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class ListNeighbourActivityTest {

    @Rule
    public ActivityTestRule<ListNeighbourActivity> mActivityTestRule = new ActivityTestRule<>(ListNeighbourActivity.class);

    @Test
    public void listNeighbourActivityTest() {
        ViewInteraction recyclerView = onView(withId(R.id.list_neighbours));
        recyclerView.perform(actionOnItemAtPosition(1, click()));
        //click on the 2nd neighbour in the list, display detail view

        //add to favorites and check the name
        ViewInteraction floatingActionButton = onView(withId(R.id.fab));
                        isDisplayed();
        floatingActionButton.perform(click());

        ViewInteraction textView = onView(withId(R.id.name));
                        isDisplayed();
        textView.check(matches(withText("Jack")));

        //Go back to neighbours list and swipe to favorites's tab
        ViewInteraction appCompatImageButton = onView(withContentDescription("Revenir en haut de la page"));
        appCompatImageButton.perform(click());

//        ViewInteraction tabView = onView(withContentDescription("Favorites"));
//        tabView.perform(click());

        ViewInteraction viewPager = onView(withId(R.id.container));
        viewPager.perform(swipeLeft());

        //check if a delete button and the favorites list are displayed
        ViewInteraction imageButton = onView(
                allOf(withId(R.id.item_list_delete_button),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.list_favorites),
                                        0),
                        2),
                        isDisplayed()));
        imageButton.check(matches(isDisplayed()));

        ViewInteraction recyclerView2 = onView(withId(R.id.list_favorites));
//                        withParent(allOf(withId(R.id.container),
//                                childAtPosition(
//                                        withId(R.id.main_content),
//                                        1))),
//                        isDisplayed()));
        recyclerView2.check(matches(isDisplayed()));
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
