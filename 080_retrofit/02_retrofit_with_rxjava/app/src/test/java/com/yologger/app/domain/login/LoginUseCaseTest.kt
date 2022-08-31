package com.yologger.app.domain.login

import com.yologger.app.domain.repository.AuthRepository
import com.yologger.app.scheduler.RxSchedulerRule
import io.reactivex.rxjava3.core.Observable
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.ArgumentMatchers.anyString
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock

class LoginUseCaseTest {

    @Mock
    lateinit var authRepository: AuthRepository;

    lateinit var loginUseCase: LoginUseCase

    @get:Rule
    val rule = RxSchedulerRule()

    @Before
    fun setUp() {
        authRepository = mock(AuthRepository::class.java)
        loginUseCase = LoginUseCase(authRepository)
    }

    @Test
    fun `로그인 성공 테스트`() {
        // Given
        val dummyEmail = "dummy@gmail.com"
        val dummyPassword = "password"
        val dummyAccessToken = "access_token"
        val dummyRefreshToken = "refresh_token"
        val dummyLoginData = LoginData(accessToken = dummyAccessToken, refreshToken = dummyRefreshToken)
        val dummyLoginResult = LoginResult.SUCCESS(dummyLoginData)

        // When
        `when`(authRepository.login(anyString(), anyString())).thenReturn(Observable.just(dummyLoginResult))

        // Then
        val params = LoginUseCase.Params(email = dummyEmail, password = dummyPassword)
        loginUseCase.execute(params)
            .test()
            .assertNoErrors()
            .assertValue { result -> result is LoginResult.SUCCESS }
            .assertValue { result -> (result as LoginResult.SUCCESS).data.accessToken == dummyAccessToken }
            .assertValue { result -> (result as LoginResult.SUCCESS).data.refreshToken == dummyRefreshToken }
    }
}