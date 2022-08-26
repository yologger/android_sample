package com.yologger.app.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.yologger.app.R
import com.yologger.app.api.auth.AuthApi
import com.yologger.app.api.auth.LoginRequest
import com.yologger.app.api.auth.LoginResponse
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import okhttp3.ResponseBody
import retrofit2.HttpException
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.Exception
import java.net.ConnectException

class MainActivity : AppCompatActivity() {

    private val button: Button by lazy { findViewById(R.id.button) }
    private lateinit var authApi: AuthApi

    companion object {
        const val BASE_URL = "http://10.0.2.2:8080"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val client = OkHttpClient.Builder().build()

        authApi = Retrofit.Builder()
            .client(client)
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(AuthApi::class.java)

        button.setOnClickListener {
            val request = LoginRequest("ronalo@gmail.com", "1234")
            GlobalScope.launch {
                try {
                    val response: LoginResponse = authApi.login(request)
                    println(response)
                } catch (exception: Exception) {
                    when(exception) {
                        is HttpException -> {
                            // 400, 500 Error
                            val code: Int = exception.code()
                            val errorBody: ResponseBody? = exception.response()?.errorBody()
                        }
                        is ConnectException -> {
                            // Connection Error
                            println(exception)
                        }
                        else -> {
                            // Other Exceptions
                        }
                    }
                }

            }
        }
    }
}