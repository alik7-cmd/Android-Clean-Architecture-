/*
package com.techascent.cleanarchitecture2.presentation

import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.filters.MediumTest
import com.techascent.cleanarchitecture2.R
import com.techascent.cleanarchitecture2.launchFragmentInHiltContainer
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.HiltTestApplication
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import org.robolectric.annotation.Config

@MediumTest
@HiltAndroidTest
@ExperimentalCoroutinesApi
class UserFragmentTest{


    @get:Rule
    val hiltRule = HiltAndroidRule(this)

    @Before
    fun setup() {
        hiltRule.inject()
    }
    @Test
    fun testNextButton_gotoRepoFragment(){
        val navController = mock(NavController::class.java)

        launchFragmentInHiltContainer<UserFragment> {
            Navigation.setViewNavController(requireView(), navController)
        }

        onView(withId(R.id.btn_next)).perform(click())

        verify(navController).navigate(
            UserFragmentDirections.actionFirstFragmentToSecondFragment()
        )
    }

}*/
