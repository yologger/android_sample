package com.yologger.koin.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import com.yologger.koin.domain.LoginUseCase

class LoginViewModel(
    private val loginUseCase: LoginUseCase
) : ViewModel() {
    fun login() {
        loginUseCase.execute()
    }
}