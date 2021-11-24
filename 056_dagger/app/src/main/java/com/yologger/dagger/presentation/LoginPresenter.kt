package com.yologger.dagger.presentation

import android.util.Log
import com.yologger.dagger.domain.LoginUseCase
import javax.inject.Inject

class LoginPresenter constructor(
    private val loginUseCase: LoginUseCase
) {
    fun login() {
        loginUseCase.execute()
    }
}