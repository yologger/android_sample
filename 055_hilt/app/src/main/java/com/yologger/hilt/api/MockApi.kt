package com.yologger.hilt.api

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface MockApi {
    @POST("/json/1")
    fun get(): Call<ResponseBody>
}