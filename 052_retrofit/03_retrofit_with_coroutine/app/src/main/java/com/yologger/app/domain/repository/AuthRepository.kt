package com.yologger.app.domain.repository

import com.yologger.app.domain.login.LoginResult

interface AuthRepository {
    suspend fun login(email: String, password: String): LoginResult
}