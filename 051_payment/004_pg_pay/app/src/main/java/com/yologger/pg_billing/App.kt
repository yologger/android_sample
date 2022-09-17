package com.yologger.pg_billing

import android.app.Application
import com.iamport.sdk.domain.core.Iamport

class App: Application() {
    override fun onCreate() {
        super.onCreate()
        Iamport.create(this)
    }
}