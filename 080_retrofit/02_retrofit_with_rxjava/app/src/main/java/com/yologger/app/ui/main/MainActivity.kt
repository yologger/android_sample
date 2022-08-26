package com.yologger.app.ui.main

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.yologger.app.R
import com.yologger.app.data.api.auth.AuthApi
import com.yologger.app.data.api.auth.LoginRequest
import okhttp3.OkHttpClient
import okhttp3.ResponseBody
import retrofit2.HttpException
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.net.ConnectException

class MainActivity : AppCompatActivity() {

    private val button: Button by lazy { findViewById(R.id.button) }
    private lateinit var authApi: AuthApi

    companion object {
        val BASE_URL = "http://10.0.2.2:8080"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Set up retrofit
        val client = OkHttpClient.Builder()
            .build()

        authApi = Retrofit.Builder()
            .client(client)
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build()
            .create(AuthApi::class.java)

        // Set up button
        button.setOnClickListener {
            val request = LoginRequest("paul@gmail.com", "1234")
            authApi.login(request)
                .doOnError { error ->

                }
                .subscribe({ response ->
                    println(response)
                }, { error ->
                    when (error) {
                        is HttpException -> {
                            // 400, 500 Error
                            val errorCode: Int = error.code()
                            val errorBody: ResponseBody? = error.response()?.errorBody()
                        }
                        is ConnectException -> {
                            // Connection Error
                            val message = error.message
                        }
                        else -> {

                        }
                    }
                }, {
                })

        }
    }
}