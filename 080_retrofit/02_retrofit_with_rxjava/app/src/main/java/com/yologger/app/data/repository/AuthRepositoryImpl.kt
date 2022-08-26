package com.yologger.app.data.repository

import androidx.lifecycle.Transformations.map
import com.google.gson.Gson
import com.yologger.app.data.api.ErrorResponse
import com.yologger.app.data.api.auth.AuthApi
import com.yologger.app.data.api.auth.LoginErrorCode
import com.yologger.app.data.api.auth.LoginRequest
import com.yologger.app.domain.login.LoginData
import com.yologger.app.domain.login.LoginError
import com.yologger.app.domain.login.LoginResult
import com.yologger.app.domain.repository.AuthRepository
import io.reactivex.rxjava3.core.Observable
import okhttp3.ResponseBody
import retrofit2.HttpException
import retrofit2.Retrofit
import java.net.ConnectException
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val authApi: AuthApi,
    private val gson: Gson
): AuthRepository {
    override fun login(email: String, password: String): Observable<LoginResult> {
        val request = LoginRequest(email = email, password = password)
        return authApi.login(request)
            .map<LoginResult> { response ->
                val loginData = LoginData(response.accessToken, response.refreshToken)
                LoginResult.SUCCESS(loginData)
            }
            .onErrorReturn { error ->
                when (error) {
                    is HttpException -> {
                        // 4xx, 5xx Error
                        val errorResponse = gson.fromJson(error.response()!!.errorBody()!!.string(), ErrorResponse::class.java)
                        when (errorResponse.code) {
                            LoginErrorCode.INVALID_EMAIL -> LoginResult.FAILURE(LoginError.INVALID_EMAIL)
                            LoginErrorCode.INVALID_PASSWORD -> LoginResult.FAILURE(LoginError.INVALID_PASSWORD)
                            else -> LoginResult.FAILURE(LoginError.NETWORK_ERROR)
                        }
                    }
                    is ConnectException -> {
                        // Connection Error
                        LoginResult.FAILURE(LoginError.NETWORK_ERROR)
                    }
                    else -> {
                        // Other Errors
                        LoginResult.FAILURE(LoginError.UNKNOWN_ERROR)
                    }
                }
            }

    }
}