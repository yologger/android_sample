package com.yologger.app.ui.login

import com.yologger.app.domain.login.LoginError
import com.yologger.app.domain.login.LoginResult
import com.yologger.app.domain.login.LoginUseCase
import com.yologger.app.ui.base.BaseViewModel
import com.yologger.app.ui.util.SingleLiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.kotlin.addTo
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase
) : BaseViewModel() {

    private val _liveState = SingleLiveEvent<State>()
    val liveState = _liveState

    sealed class State {
        object LoginSuccess: State()
        data class LoginFailure(val error: Error): State()
    }

    enum class Error {
        INVALID_EMAIL,
        INVALID_PASSWORD,
        NETWORK_ERROR,
        CLIENT_ERROR
    }

    fun login() {
        val params = LoginUseCase.Params("paul@gmail.com", "1234")
        loginUseCase.execute(params)
            .subscribe { result ->
                when (result) {
                    is LoginResult.SUCCESS -> _liveState.value = State.LoginSuccess
                    is LoginResult.FAILURE -> {
                        when (result.error) {
                            LoginError.INVALID_EMAIL -> _liveState.value = State.LoginFailure(Error.INVALID_EMAIL)
                            LoginError.INVALID_PASSWORD -> _liveState.value = State.LoginFailure(Error.INVALID_PASSWORD)
                            LoginError.NETWORK_ERROR -> _liveState.value = State.LoginFailure(Error.NETWORK_ERROR)
                            else -> _liveState.value = State.LoginFailure(Error.CLIENT_ERROR)
                        }
                    }
                }
            }.addTo(disposables)
    }
}