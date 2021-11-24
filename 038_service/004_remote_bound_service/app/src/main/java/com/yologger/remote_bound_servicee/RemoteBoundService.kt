package com.yologger.remote_bound_servicee

import android.app.Service
import android.content.Intent
import android.os.Handler
import android.os.IBinder
import android.os.Message
import android.os.Messenger
import android.util.Log
import android.widget.Toast
import kotlin.coroutines.coroutineContext

class RemoteBoundService : Service() {

    val messenger = Messenger(MessageHandler())

    override fun onBind(intent: Intent): IBinder {
        Log.d("TEST", "onBind()")
        return messenger.binder
    }

    class MessageHandler: Handler() {
        override fun handleMessage(message: Message) {
            val data = message.data
            val value = data.getString("message")
            value?.run {
                Log.d("TEST", value)
            }
        }
    }
}