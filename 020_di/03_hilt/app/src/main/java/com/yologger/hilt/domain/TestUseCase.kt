package com.yologger.hilt.domain

import android.util.Log
import com.yologger.hilt.data.TestRepository
import javax.inject.Inject

class TestUseCase @Inject constructor(
    private val testRepository: TestRepository
) {
    fun execute() {
        Log.d("KKK", "execute() from TestUseCase")
        testRepository.test()
    }
}