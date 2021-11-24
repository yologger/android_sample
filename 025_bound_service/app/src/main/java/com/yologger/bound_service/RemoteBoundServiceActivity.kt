package com.yologger.bound_service

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.*
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button
import android.widget.EditText

class RemoteBoundServiceActivity : AppCompatActivity() {

    val editText: EditText by lazy { findViewById<EditText>(R.id.activity_remote_bound_service_et) }
    val button: Button by lazy { findViewById<Button>(R.id.activity_remote_bound_service_btn) }

    private var messenger: Messenger? = null


    private var isBound = false

    private var connection = object: ServiceConnection {

        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            messenger = Messenger(service)
            isBound = true
        }

        override fun onServiceDisconnected(name: ComponentName?) {
            messenger = null
            isBound = false
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_remote_bound_service)

        var intent = Intent(applicationContext, RemoteBoundService::class.java)
        bindService(intent, connection, Context.BIND_AUTO_CREATE)

        button.setOnClickListener {
            val message = Message.obtain()

            var  bundle = Bundle()
            bundle.putString("msg", editText.text.toString())

            message.data = bundle

            try {
                messenger?.send(message)
            } catch (e: RemoteException) {
                e.printStackTrace()
            }


        }
    }

    override fun onStop() {
        super.onStop()
        // Unbind from the service
        if (isBound) {
            unbindService(connection)
            isBound = false
        }
    }
}