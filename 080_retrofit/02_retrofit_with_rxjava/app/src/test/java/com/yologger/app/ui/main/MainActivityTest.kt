package com.yologger.app.ui.main

import com.google.common.truth.Truth.assertThat
import com.yologger.app.databinding.ActivityMainBinding
import dagger.hilt.android.testing.*
import org.checkerframework.checker.units.qual.h
import org.junit.Assert.fail
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner
import org.robolectric.android.controller.ActivityController
import org.robolectric.annotation.Config
import java.lang.Exception

@RunWith(RobolectricTestRunner::class)
@HiltAndroidTest
@Config(application = HiltTestApplication::class)
class MainActivityTest {

    @get:Rule
    val hiltRule = HiltAndroidRule(this)

    lateinit var activityController: ActivityController<MainActivity>
    lateinit var activity: MainActivity
    lateinit var binding: ActivityMainBinding

    @BindValue
    @MainViewModelQualifier
    @Mock
    val mainViewModel: MainViewModel = mock(MainViewModel::class.java)

    @Before
    fun setUp() {
        hiltRule.inject()
    }

    @Test
    fun test() {
        `when`(mainViewModel.buttonText).thenReturn("BEFORE")

        activityController = Robolectric.buildActivity(MainActivity::class.java)
        activityController
            .create()
            .start()
            .resume()

        activity = activityController.get()

        try {
            val field = activity::class.java.getDeclaredField("binding")
            field.isAccessible = true
            binding = field.get(activity) as ActivityMainBinding

            assertThat(mainViewModel.buttonText).isEqualTo("BEFORE")
            assertThat(binding.button.text).isEqualTo("LOG IN")

            binding.button.performClick()

            assertThat(binding.button.text).isEqualTo("AFTER")

        } catch (e: Exception) {
            fail()
        } finally {
            activityController
                .pause()
                .stop()
                .destroy()
        }
    }
}