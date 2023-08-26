package com.currency.converter.presentation

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.filters.MediumTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Assert.*
import org.junit.Test
import dagger.hilt.android.testing.HiltAndroidTest
import com.currency.converter.R

@MediumTest
@HiltAndroidTest
@ExperimentalCoroutinesApi
class MainActivityTest {


    @Test
    fun test_launch_main() {
        ActivityScenario.launch(MainActivity::class.java).onActivity {
            Espresso.onView(withId(R.id.main_fragment_container))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

        }
    }
}