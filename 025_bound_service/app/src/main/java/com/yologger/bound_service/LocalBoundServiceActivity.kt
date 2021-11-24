package com.yologger.bound_service

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.IBinder
import android.widget.Button
import android.widget.TextView

class LocalBoundServiceActivity : AppCompatActivity() {

    val button: Button by lazy { findViewById<Button>(R.id.activity_local_bound_service_btn) }
    val textView: TextView by lazy { findViewById<TextView>(R.id.activity_local_bound_service_tv) }

    private lateinit var service: LocalBoundService
    private var isBound = false

    private val connection = object : ServiceConnection {

        // 서비스가 연결되었을 때 호출
        override fun onServiceConnected(name: ComponentName?, binder: IBinder?) {
            println("hello 1")
            var localBinder = binder as LocalBoundService.LocalBinder
            service = localBinder.getService()
            println("hello 2")
            isBound = true
        }

        // 서비스가 끊겼을 때 호출
        override fun onServiceDisconnected(name: ComponentName?) {
            isBound = false
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_local_bound_service)

        var intent = Intent(this, LocalBoundService::class.java)
        bindService(intent, connection, Context.BIND_AUTO_CREATE)

        button.setOnClickListener {
            var currentTime = service.getCurrentTime()
            textView.text = currentTime
        }
    }

    override fun onStop() {
        super.onStop()
        unbindService(connection)
        isBound = false
    }
}