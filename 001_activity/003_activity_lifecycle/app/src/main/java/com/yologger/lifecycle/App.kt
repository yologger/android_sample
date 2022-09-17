package com.yologger.lifecycle

import android.app.Application

class App: Application() {

    var url = "https://www.naver.com"

    override fun onCreate() {
        super.onCreate()
    }
}