package com.yologger.dagger.data

import android.util.Log

class UserRepositoryImpl: UserRepository {
    override fun login() {
        Log.d("KKK", "login() from UserRepositoryImpl")
    }
}