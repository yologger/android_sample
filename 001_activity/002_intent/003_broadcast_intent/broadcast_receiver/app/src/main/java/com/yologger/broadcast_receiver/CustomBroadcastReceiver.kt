package com.yologger.broadcast_receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast

class CustomBroadcastReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        intent.action?.run {
            when(this) {
                Intent.ACTION_POWER_CONNECTED -> {
                    Toast.makeText(context, "ACTION POWER CONNECTED", Toast.LENGTH_SHORT).show()
                }
                Intent.ACTION_POWER_DISCONNECTED -> {
                    Toast.makeText(context, "ACTION POWER DISCONNECTED", Toast.LENGTH_SHORT).show()
                }
                else -> {
                    intent?.extras?.getString("name").run {
                        Toast.makeText(context, "$this", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }
}