package com.yologger.app.api.auth

import com.yologger.app.api.auth.model.LoginRequest
import com.yologger.app.api.auth.model.LoginResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApi {
    @POST("/auth/login")
    fun login(@Body request: LoginRequest): Call<LoginResponse>
}