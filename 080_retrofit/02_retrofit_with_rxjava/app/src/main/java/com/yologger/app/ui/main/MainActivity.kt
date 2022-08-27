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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Set up button
        button.setOnClickListener {
            finish()
        }
    }
}