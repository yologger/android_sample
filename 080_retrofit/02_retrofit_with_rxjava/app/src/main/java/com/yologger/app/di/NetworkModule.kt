package com.yologger.app.di

import com.yologger.app.Constant
import com.yologger.app.data.api.auth.AuthApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Singleton
    @Provides
    fun providesOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .connectTimeout(Constant.CONNECTION_TIMEOUT_DURATION, TimeUnit.SECONDS)
            .readTimeout(Constant.READ_TIMEOUT_DURATION, TimeUnit.SECONDS)
            .writeTimeout(Constant.WRITE_TIMEOUT_DURATION, TimeUnit.SECONDS)
            .build()
    }

    @Singleton
    @Provides
    fun providesAuthService(okHttpClient: OkHttpClient): AuthApi {
        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(Constant.BASE_URL)
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(AuthApi::class.java)
    }
}