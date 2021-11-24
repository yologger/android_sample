package com.yologger.project

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel: ViewModel() {

    val liveEmail: MutableLiveData<String> = MutableLiveData<String>().apply { value = "" }
    val livePassword: MutableLiveData<String> = MutableLiveData<String>().apply { value = "" }

    fun login() {
        Log.d("KKK", "${liveEmail.value} and ${livePassword.value}")
    }
}