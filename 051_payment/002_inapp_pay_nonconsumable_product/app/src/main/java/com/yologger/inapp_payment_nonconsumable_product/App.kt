package com.yologger.inapp_payment_nonconsumable_product

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