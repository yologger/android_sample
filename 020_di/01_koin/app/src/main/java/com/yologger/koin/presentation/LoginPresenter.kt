package com.yologger.koin.presentation

import com.yologger.koin.domain.LoginUseCase

class LoginPresenter(
    private val loginUseCase: LoginUseCase
) {
    fun login() {
        loginUseCase.execute()
    }
}