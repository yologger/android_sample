package com.yologger.app.domain.login

import com.yologger.app.domain.base.BaseUseCase
import com.yologger.app.domain.repository.AuthRepository

class LoginUseCase constructor(
    private val authRepository: AuthRepository
) : BaseUseCase<LoginUseCase.Params, LoginResult> {

    data class Params(val email: String, val password: String)

    override suspend fun execute(params: Params?): LoginResult {
        params?.let {
            return authRepository.login(params.email, params.password)
        } ?: run {
            return LoginResult.Failure(LoginError.BAD_REQUEST)
        }
    }
}