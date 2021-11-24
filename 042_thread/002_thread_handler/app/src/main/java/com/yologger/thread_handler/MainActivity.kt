package com.yologger.thread_handler

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    val button: Button by lazy { findViewById<Button>(R.id.activity_main_btn) }
    val textView: TextView by lazy { findViewById<TextView>(R.id.activity_main_tv) }

    val handler = object: Handler() {
        override fun handleMessage(msg: Message) {
            super.handleMessage(msg)
            val bundle = msg.data
            val string = bundle.getString("text")
            string?.let {
                textView.text = it
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button.setOnClickListener {
            var runnable = Runnable {
                Thread.sleep(10 * 1000)     //  10 seconds

                val message = handler.obtainMessage()
                val bundle = Bundle()
                bundle.putString("text", "Hello World")
                message.data = bundle
                handler.sendMessage(message)
            }

            var thread = Thread(runnable)
            thread.start()
        }
    }
}