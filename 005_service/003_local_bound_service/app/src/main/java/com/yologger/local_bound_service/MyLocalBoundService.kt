package com.yologger.local_bound_service

import android.app.Service
import android.content.Intent
import android.os.IBinder

class MyLocalBoundService : Service() {

    override fun onBind(intent: Intent): IBinder {
        TODO("Return the communication channel to the service.")
    }
}