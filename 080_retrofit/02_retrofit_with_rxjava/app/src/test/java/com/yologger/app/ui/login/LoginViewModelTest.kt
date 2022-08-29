package com.yologger.app.ui.login

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth.assertThat
import com.yologger.app.domain.login.LoginData
import com.yologger.app.domain.login.LoginError
import com.yologger.app.domain.login.LoginResult
import com.yologger.app.domain.login.LoginUseCase
import io.reactivex.rxjava3.core.Observable
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.*

class LoginViewModelTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @Mock
    lateinit var loginUseCase: LoginUseCase

    lateinit var loginViewModel: LoginViewModel

    @Before
    fun setUp() {
        loginUseCase = mock(LoginUseCase::class.java)
        loginViewModel = LoginViewModel(loginUseCase)
    }

    @Test
    fun `로그인 성공 테스트`() {
        // Given
        val dummyAccessToken = "access_token"
        val dummyRefreshToken = "refresh_token"
        val dummyLoginData = LoginData(accessToken = dummyAccessToken, refreshToken = dummyRefreshToken)
        val dummyLoginResult = LoginResult.SUCCESS(dummyLoginData)

        // When
        `when`(loginUseCase.execute(any())).thenReturn(Observable.just(dummyLoginResult))
        loginViewModel.login()

        // Then
        assertThat(loginViewModel.liveState.value).isEqualTo(LoginViewModel.State.LoginSuccess)
    }

    @Test
    fun `로그인 실패 테스트 - 잘못된 이메일`() {
        // Given
        val dummyLoginResult = LoginResult.FAILURE(LoginError.INVALID_EMAIL)

        // When
        `when`(loginUseCase.execute(any())).thenReturn(Observable.just(dummyLoginResult))
        loginViewModel.login()

        // Then
        assertThat(loginViewModel.liveState.value is LoginViewModel.State.LoginFailure).isTrue()
        assertThat(((loginViewModel.liveState.value as LoginViewModel.State.LoginFailure)).error).isEqualTo(LoginViewModel.Error.INVALID_EMAIL)
    }
}