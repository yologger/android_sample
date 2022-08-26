package com.yologger.app.ui.login

import com.yologger.app.domain.login.LoginError
import com.yologger.app.domain.login.LoginResult
import com.yologger.app.domain.login.LoginUseCase
import com.yologger.app.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.kotlin.addTo
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase
) : BaseViewModel() {

    fun login() {
        val params = LoginUseCase.Params("paul@gmail.com", "1234")
        loginUseCase.execute(params)
            .subscribe { result ->
                when (result) {
                    is LoginResult.SUCCESS -> {
                        println("login success")
                        println("Access token: ${result.data.accessToken}")
                        println("Refresh token: ${result.data.refreshToken}")
                    }
                    is LoginResult.FAILURE -> {
                        when (result.error) {
                            LoginError.INVALID_EMAIL -> { println("Invalid Email") }
                            LoginError.INVALID_PASSWORD -> { println("Invalid Password") }
                            else -> { println("Unknown Error") }
                        }
                    }
                }
            }.addTo(disposables)
    }
}