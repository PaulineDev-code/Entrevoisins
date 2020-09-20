package com.openclassrooms.entrevoisins.ui.neighbour_list;


import android.support.test.espresso.ViewInteraction;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.utils.DeleteViewAction;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.swipeLeft;
import static android.support.test.espresso.action.ViewActions.swipeRight;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static android.support.test.espresso.matcher.ViewMatchers.hasMinimumChildCount;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withContentDescription;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withParent;
import static com.openclassrooms.entrevoisins.utils.RecyclerViewItemCountAssertion.withItemCount;
import static org.hamcrest.Matchers.allOf;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class FabTest {

    private static int ITEMS_COUNT = 2;

    @Rule
    public ActivityTestRule<ListNeighbourActivity> mActivityTestRule = new ActivityTestRule<>(ListNeighbourActivity.class);

    @Test
    public void fabTest() {
        ViewInteraction recyclerView = onView(withId(R.id.list_neighbours));

        recyclerView.perform(actionOnItemAtPosition(1, click()));
        //go to detail view

        ViewInteraction floatingActionButton = onView(withId(R.id.fab));
        floatingActionButton.check(matches(isDisplayed()));
        floatingActionButton.perform(click());
        //add to favorites and check if the button is displayed

        ViewInteraction appCompatImageButton = onView(withContentDescription("Revenir en haut de la page"));
        appCompatImageButton.perform(click());

        ViewInteraction viewPager = onView(withId(R.id.container));
        viewPager.perform(swipeRight());
        //We go back to Neighbours list and ensure we're in the recyclerview

        recyclerView.perform(actionOnItemAtPosition(3, click()));
        floatingActionButton.perform(click());

        appCompatImageButton.perform(click());

        viewPager.perform(swipeLeft());

        ViewInteraction recyclerFavorites = onView(ViewMatchers.withId(R.id.list_favorites));
        recyclerFavorites.check(withItemCount(ITEMS_COUNT));

        recyclerFavorites.perform(RecyclerViewActions.actionOnItemAtPosition(1, new DeleteViewAction()));
        recyclerFavorites.check(withItemCount(ITEMS_COUNT-1));
    }

}
