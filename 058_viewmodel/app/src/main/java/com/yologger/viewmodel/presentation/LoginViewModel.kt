package com.yologger.viewmodel.presentation

import androidx.lifecycle.ViewModel
import com.yologger.viewmodel.domain.LoginUseCase

class LoginViewModel(
    private val loginUseCase: LoginUseCase
) : ViewModel() {

    fun login() {
         loginUseCase.execute()
    }
}