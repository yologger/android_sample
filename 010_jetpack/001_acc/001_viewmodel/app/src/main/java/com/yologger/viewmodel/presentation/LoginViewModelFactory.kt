package com.yologger.viewmodel.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.yologger.viewmodel.domain.LoginUseCase
import java.lang.IllegalArgumentException

class LoginViewModelFactory(private val loginUseCase: LoginUseCase) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(LoginViewModel::class.java)) {
            LoginViewModel(loginUseCase) as T
        } else {
            throw IllegalArgumentException()
        }
    }
}