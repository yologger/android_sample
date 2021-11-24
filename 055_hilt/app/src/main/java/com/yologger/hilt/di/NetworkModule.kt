package com.yologger.hilt.di

import com.yologger.hilt.api.MockApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    fun provideMockApi(): MockApi {
        val okHttpClient = OkHttpClient.Builder()
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl("http://drg6v.mocklab.io")
            .client(okHttpClient)
            .build()

        val api = retrofit.create(MockApi::class.java)
        return api
    }
}