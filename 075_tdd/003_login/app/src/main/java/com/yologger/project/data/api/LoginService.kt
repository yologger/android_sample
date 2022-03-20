package com.yologger.project.data.api

import com.yologger.project.data.model.LoginRequest
import com.yologger.project.data.model.LoginResponse
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.Call

interface LoginService {
    @POST("/api/auth/login")
    fun login(@Body request: LoginRequest): Call<LoginResponse>
}