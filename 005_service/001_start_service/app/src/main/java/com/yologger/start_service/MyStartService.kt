package com.yologger.start_service

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log

class MyStartService : Service() {

    override fun onCreate() {
        super.onCreate()
        Log.d("TEST", "[MyStartService] onCreate()")
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.d("TEST", "[MyStartService] onStartCommand()")

//        while (true) {
//            Thread.sleep(1000 * 3)     // 10 seconds
//        }

        var runnable = Runnable {
            while (true) {
                Log.d("TEST", "This is service.")
                Thread.sleep(1000 * 3)     // 10 seconds
            }
        }

        // 별도의 스레드 생성
        var thread = Thread(runnable)

        // 스레드 시작
        thread.start()

        return super.onStartCommand(intent, flags, startId)
    }

    override fun onBind(intent: Intent?): IBinder? {
        Log.d("TEST", "[MyStartService] onBind()")
        return null
    }

    override fun onDestroy() {
        Log.d("TEST", "[MyStartService] onDestroy()")
        super.onDestroy()


    }
}