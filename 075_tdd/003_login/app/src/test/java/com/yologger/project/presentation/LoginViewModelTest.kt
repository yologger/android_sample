package com.yologger.project.presentation

import com.yologger.project.domain.LoginUseCase
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock

class LoginViewModelTest {

    @Mock
    lateinit var mockLoginUseCase: LoginUseCase

    @Before
    fun setUp() {
        mockLoginUseCase = mock(LoginUseCase::class.java)
    }
}