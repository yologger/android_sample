package com.yologger.app.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import com.yologger.app.R
import com.yologger.app.api.auth.AuthApi
import com.yologger.app.api.auth.model.LoginRequest
import com.yologger.app.api.auth.model.LoginResponse
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.logging.Logger

class MainActivity : AppCompatActivity() {

    private val button: Button by lazy { findViewById(R.id.button) }

    private lateinit var authApi: AuthApi

    companion object {
        val BASE_URL = "http://10.0.2.2:8081"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val client = OkHttpClient.Builder()
            .build()

        authApi = Retrofit.Builder()
            .client(client)
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(AuthApi::class.java)

        button.setOnClickListener {
            val request = LoginRequest("paul@gmail.com", "1234")

            authApi.login(request).enqueue(object : Callback<LoginResponse> {
                override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                    if (response.isSuccessful) {
                        Log.e("ERROR", response.code().toString())
                        Log.e("ERROR", response.body().toString())
                    } else {
                        // 400, 500 Error
                        Log.e("ERROR", response.code().toString())
                        Log.e("ERROR", response.errorBody().toString())
                        val code = response.code()
                        val errorBody = response.errorBody()
                    }
                }

                override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                    // Connection Error
                    Log.e("ERROR", t.localizedMessage)
                }
            })
        }
    }
}