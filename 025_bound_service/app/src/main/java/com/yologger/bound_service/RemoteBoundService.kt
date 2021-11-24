package com.yologger.bound_service

import android.app.Service
import android.content.Intent
import android.os.Handler
import android.os.IBinder
import android.os.Message
import android.os.Messenger
import android.util.Log
import android.widget.Toast

class RemoteBoundService : Service() {

    val messenger = Messenger(MessageHandler())

    override fun onBind(intent: Intent): IBinder {
        return messenger.binder
    }

    class MessageHandler: Handler() {
        override fun handleMessage(msg: Message) {
            super.handleMessage(msg)
            var data = msg.data
            var message = data.getString("msg")
            Log.d("TEST", message!!)
        }
    }
}