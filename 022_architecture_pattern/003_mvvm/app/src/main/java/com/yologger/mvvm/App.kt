package com.yologger.mvvm

import android.app.Application

class App: Application() {

    companion object {
        private lateinit var instance: App

        fun newInstance(): App {
            return instance
        }
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}