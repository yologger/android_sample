package com.yologger.socket.client

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import java.io.DataInputStream
import java.io.DataOutputStream
import java.net.InetSocketAddress
import java.net.Socket
import java.net.SocketAddress

class MainActivity : AppCompatActivity() {

    companion object {
        // const val SERVER_URL = "http://10.0.2.2"
        // const val SERVER_URL = "localhost"
        const val SERVER_URL = "10.0.2.2"
        const val PORT = 8888
    }

    private var socket: Socket? = null

    private val editText: EditText by lazy { findViewById(R.id.activity_main_editText) }
    private val button: Button by lazy { findViewById(R.id.activity_main_button) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button.setOnClickListener {

            Thread(Runnable {
                try {
                    val socket = Socket(SERVER_URL, PORT)
                    val dataOutputStream = DataOutputStream(socket.getOutputStream())
                    val dataInputStream = DataInputStream(socket.getInputStream())
                    dataOutputStream.writeUTF(editText.text.toString())
                    val data = dataInputStream.readUTF()
                    runOnUiThread {
                        val toast = Toast.makeText(this@MainActivity, "Data from server: ${data}", Toast.LENGTH_SHORT)
                        toast.setGravity(Gravity.CENTER_HORIZONTAL or Gravity.CENTER_HORIZONTAL, 0, 0)
                        toast.show()
                    }

                } catch (e: Exception) {
                    Log.d("KKK", e.localizedMessage)
                }

            }).start()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        socket?.run {
            close()
        }
    }
}