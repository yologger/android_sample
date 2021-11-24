package com.yologger.bound_service

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import java.text.SimpleDateFormat
import java.util.*

class LocalBoundService : Service() {

    private val binder = LocalBinder()

    override fun onBind(intent: Intent): IBinder {
        return binder
    }

    // 현재 시간 반환
    fun getCurrentTime(): String {
        var dateFormat = SimpleDateFormat("HH:mm:ss MM/dd/yyyy", Locale.US)
        return dateFormat.format(Date())
    }

    inner class LocalBinder : Binder() {
        fun getService(): LocalBoundService = this@LocalBoundService
    }
}