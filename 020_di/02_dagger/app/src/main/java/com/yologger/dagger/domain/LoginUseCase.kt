package com.yologger.dagger.domain

import android.util.Log
import com.yologger.dagger.data.UserRepository
import javax.inject.Inject

class LoginUseCase constructor(
    private val userRepository: UserRepository
){
    fun execute() {
        userRepository.login()
    }
}