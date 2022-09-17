package com.yologger.mvvm

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel: ViewModel() {

    val repository = Repository()
    var counter: MutableLiveData<String> = MutableLiveData()

    init {
        var initValue = repository.get()
        counter.value = initValue.toString()
    }

    fun plus() {
        repository.plus()
        var newValue = repository.get()
        counter.value = newValue.toString()
    }

    fun minus() {
        repository.minus()
        var newValue = repository.get()
        counter.value = newValue.toString()
    }
}