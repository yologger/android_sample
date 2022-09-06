package com.yologger.app.domain.login

import com.google.common.truth.Truth.assertThat
import com.yologger.app.domain.repository.AuthRepository
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.anyString
import org.mockito.Mockito.mock


class LoginUseCaseTest {

    @Mock
    lateinit var authRepository: AuthRepository

    lateinit var loginUseCase: LoginUseCase

    @Before
    fun setUp() {
        authRepository = mock(AuthRepository::class.java)
        loginUseCase = LoginUseCase(authRepository)
    }

    @Test
    fun `로그인 성공 테스트`() = runBlocking {
        // Given
        val dummyEmail = "dummy@gmail.com"
        val dummyPassword = "password"
        val dummyAccessToken = "access_token"
        val dummyRefreshToken = "refresh_token"
        val dummyLoginData = LoginData(accessToken = dummyAccessToken, refreshToken = dummyRefreshToken)
        val dummyLoginResult = LoginResult.Success(dummyLoginData)

        // When
        Mockito.`when`(authRepository.login(anyString(), anyString())).thenReturn(dummyLoginResult)

        // Then
        val params = LoginUseCase.Params(email = dummyEmail, password = dummyPassword)
        val result = loginUseCase.execute(params)

        assertThat(result is LoginResult.Success).isTrue()
        assertThat((result as LoginResult.Success).data.accessToken).isEqualTo(dummyAccessToken)
        assertThat((result as LoginResult.Success).data.refreshToken).isEqualTo(dummyRefreshToken)
    }

    @Test
    fun `로그인 실패 테스트 - 잘못된 이메일`() = runBlocking {
        // Given
        val dummyEmail = "dummy@gmail.com"
        val dummyPassword = "password"
        val dummyAccessToken = "access_token"
        val dummyRefreshToken = "refresh_token"
        val dummyLoginData = LoginData(accessToken = dummyAccessToken, refreshToken = dummyRefreshToken)
        val dummyLoginResult = LoginResult.Failure(LoginError.INVALID_EMAIL)

        // When
        Mockito.`when`(authRepository.login(anyString(), anyString())).thenReturn(dummyLoginResult)

        // Then
        val params = LoginUseCase.Params(email = dummyEmail, password = dummyPassword)
        val result = loginUseCase.execute(params)

        assertThat(result is LoginResult.Failure).isTrue()
        assertThat((result as LoginResult.Failure).error).isEqualTo(LoginError.INVALID_EMAIL);
    }
}