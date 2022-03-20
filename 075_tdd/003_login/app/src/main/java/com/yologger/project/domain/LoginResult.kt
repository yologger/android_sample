package com.yologger.project.domain

sealed class LoginResult {
    object SUCCESS: LoginResult()
    data class FAILURE(val error: LoginError): LoginResult()
}