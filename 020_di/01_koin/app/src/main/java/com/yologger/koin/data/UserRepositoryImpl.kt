package com.yologger.koin.data

import android.util.Log

class UserRepositoryImpl: UserRepository {
    override fun login() {
        Log.d("KKK", "login from UserRepositoryImpl")
    }
}