package com.yologger.app.data.repository

import com.google.gson.Gson
import com.yologger.app.data.api.ErrorResponse
import com.yologger.app.data.api.auth.AuthApi
import com.yologger.app.data.api.auth.LoginErrorCode
import com.yologger.app.data.api.auth.LoginRequest
import com.yologger.app.domain.login.LoginData
import com.yologger.app.domain.login.LoginError
import com.yologger.app.domain.login.LoginResult
import com.yologger.app.domain.repository.AuthRepository
import retrofit2.HttpException
import java.net.ConnectException

class AuthRepositoryImpl constructor(
    private val authApi: AuthApi,
    private val gson: Gson
) : AuthRepository {
    override suspend fun login(email: String, password: String): LoginResult {
        val request = LoginRequest(email = email, password = password)
        try {
            val response = authApi.login(request)
            val data = LoginData(accessToken = response.accessToken, refreshToken = response.refreshToken)
            return LoginResult.Success(data)
        } catch (error: Exception) {
            return when(error) {
                is HttpException -> {
                    // 4xx, 5xx Error
                    val errorResponse = gson.fromJson(error.response()!!.errorBody()!!.string(), ErrorResponse::class.java)
                    when (errorResponse.code) {
                        LoginErrorCode.INVALID_EMAIL -> LoginResult.Failure(LoginError.INVALID_EMAIL)
                        LoginErrorCode.INVALID_PASSWORD -> LoginResult.Failure(LoginError.INVALID_PASSWORD)
                        else -> LoginResult.Failure(LoginError.NETWORK_ERROR)
                    }
                }
                is ConnectException -> LoginResult.Failure(LoginError.NETWORK_ERROR)
                else -> LoginResult.Failure(LoginError.UNKNOWN_ERROR)
            }
        }
    }
}