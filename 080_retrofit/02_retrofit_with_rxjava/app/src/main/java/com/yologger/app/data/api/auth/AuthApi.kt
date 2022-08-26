package com.yologger.app.data.api.auth

import io.reactivex.rxjava3.core.Observable
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApi {
    @POST("auth/login")
    fun login(@Body request: LoginRequest): Observable<LoginResponse>
}