package com.yologger.hilt.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import com.yologger.hilt.domain.SampleUseCase
import com.yologger.hilt.domain.TestUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val testUseCase: TestUseCase,
    private val sampleUseCase: SampleUseCase
): ViewModel() {
    fun test() {
        Log.d("KKK", "test() from MainViewModel")
        testUseCase.execute()
        sampleUseCase.execute()
    }
}