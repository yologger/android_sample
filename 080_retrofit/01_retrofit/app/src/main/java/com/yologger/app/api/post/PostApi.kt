package com.yologger.app.api.post

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*

interface PostApi {

    @GET("posts")
    fun getPosts(@Query("page") page: Int, @Query("size") size: Int): Call<ResponseBody>

    @GET("post/{id}")
    fun getPostById(@Path("id") id: Long)

    @DELETE("post/{id}")
    fun deletePostById(@Path("id") id: Long)

}