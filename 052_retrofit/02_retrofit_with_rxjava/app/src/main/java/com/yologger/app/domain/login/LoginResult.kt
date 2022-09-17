package com.yologger.app.domain.login

sealed class LoginResult {
    data class SUCCESS(val data: LoginData): LoginResult()
    data class FAILURE(val error: LoginError): LoginResult()
}