package com.yologger.app.ui.login

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth.assertThat
import com.yologger.app.ui.util.SingleLiveEvent
import dagger.hilt.android.testing.BindValue
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.HiltTestApplication
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

@RunWith(RobolectricTestRunner::class)
@HiltAndroidTest
@Config(application = HiltTestApplication::class)
class LoginActivityUnitTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val hiltRule = HiltAndroidRule(this)

    lateinit var activityController: ActivityController<LoginActivity>  // 액티비티의 생명주기 제어
    lateinit var activity: LoginActivity

    @BindValue
    @Mock
    val loginViewModel: LoginViewModel = mock(LoginViewModel::class.java)

    @Before
    fun setUp() {
        hiltRule.inject()
    }

    @Test
    fun `로그인 테스트`() {
        activityController = Robolectric.buildActivity(LoginActivity::class.java)

        `when`(loginViewModel.liveState).thenReturn(SingleLiveEvent<LoginViewModel.State>())

        activityController
            .create()
            .start()
            .resume()

        activity = activityController.get()

        // ...
    }
}