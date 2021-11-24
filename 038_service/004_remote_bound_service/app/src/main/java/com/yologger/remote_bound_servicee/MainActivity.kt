package com.yologger.remote_bound_servicee

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.IBinder
import android.os.Message
import android.os.Messenger
import android.util.Log
import android.widget.Button
import android.widget.EditText

class MainActivity : AppCompatActivity() {

    val editText: EditText by lazy { findViewById<EditText>(R.id.activity_main_et) }
    val button: Button by lazy { findViewById<Button>(R.id.activity_main_btn) }

    var messenger: Messenger? = null
    var isBound = false

    var connection = object: ServiceConnection {
        override fun onServiceConnected(name: ComponentName?, binder: IBinder?) {
            messenger = Messenger(binder)
            isBound = true
        }

        override fun onServiceDisconnected(name: ComponentName?) {
            messenger = null
            isBound = false
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val intent = Intent(applicationContext, RemoteBoundService::class.java)
        bindService(intent, connection, Context.BIND_AUTO_CREATE)

        button.setOnClickListener {
            if (!isBound) return@setOnClickListener

            var message = Message.obtain()
            var bundle = Bundle()
            bundle.putString("message", editText.text.toString())
            message.data = bundle
            messenger?.send(message)
        }
    }
}