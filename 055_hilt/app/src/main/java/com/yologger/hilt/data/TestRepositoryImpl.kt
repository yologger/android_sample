package com.yologger.hilt.data

import android.util.Log
import com.yologger.hilt.api.MockApi
import retrofit2.Callback
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Response
import javax.inject.Inject

class TestRepositoryImpl @Inject constructor(
    private val api: MockApi
): TestRepository {

    var value = 1

    override fun test() {
        Log.d("KKK", "test() from TestRepositoryImpl / value:${value}")
        value += 1
        api.get().enqueue(object: Callback<ResponseBody> {
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                Log.d("KKK", "onResponse()")
                if (response.isSuccessful) {
                    Log.d("KKK", "code: ${response.code()}")
                    Log.d("KKK", "body: ${response.body()!!.string()}")
                } else {
                    Log.d("KKK", "code: ${response.code()}")
                    Log.d("KKK", "body: ${response.errorBody()!!.string()}")
                }
            }

            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                Log.d("KKK", "onFailure(): ${t.localizedMessage}")
            }
        })
    }
}