package com.currency.converter.presentation.home

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import com.currency.converter.R
import com.currency.converter.factory.TestMainFragmentsFactory
import com.currency.converter.launchFragmentInHiltContainer
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject
import javax.inject.Named


@HiltAndroidTest
class HomeFragmentTest{

    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    var instantTaskExecutorRule = InstantTaskExecutorRule()


    @Inject
    @Named("TestMainFragmentsFactory")
    lateinit var testFragmentFactory: TestMainFragmentsFactory

    @Before
    fun setup() {
        hiltRule.inject()
    }


    @Test
    fun testUpdateOnFromValue_toValueUpdated() {
        launchFragmentInHiltContainer<HomeFragment>(
            fragmentFactory = testFragmentFactory
        ) {
        }
        val newValue = "500"
        onView(withId(R.id.etFrom)).perform(ViewActions.typeText(newValue))
        Espresso.closeSoftKeyboard()
        onView(withId(R.id.etTo)).check(ViewAssertions.matches(withText("500.00")))


    }

}