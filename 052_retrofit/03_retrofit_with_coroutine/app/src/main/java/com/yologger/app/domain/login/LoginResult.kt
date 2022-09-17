package com.yologger.app.domain.login

sealed class LoginResult {
    data class Success(val data: LoginData): LoginResult()
    data class Failure(val error: LoginError): LoginResult()
}