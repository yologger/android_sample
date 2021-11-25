package com.yologger.koin.domain

import com.yologger.koin.data.UserRepository

class LoginUseCase(
    private val userRepository: UserRepository
) {
    fun execute() {
        userRepository.login()
    }
}