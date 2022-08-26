package com.yologger.app.domain.login

import com.yologger.app.domain.base.ObservableUseCase
import com.yologger.app.domain.repository.AuthRepository
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val authRepository: AuthRepository
) : ObservableUseCase<LoginUseCase.Params, LoginResult>() {
    data class Params(val email: String, val password: String)

    override fun call(params: Params?): Observable<LoginResult> {
        params?.let {
            return authRepository.login(email = params.email, password = params.password)
        } ?: run {
            return Observable.just(LoginResult.FAILURE(LoginError.BAD_REQUEST))
        }
    }
}