package com.yologger.thread

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    val buttonToast: Button by lazy { findViewById<Button>(R.id.activity_main_btn_toast) }
    val buttonStop: Button by lazy { findViewById<Button>(R.id.activity_main_btn_stop) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        buttonToast.setOnClickListener {
            Toast.makeText(this, "Toast Message", Toast.LENGTH_SHORT).show()
        }

        buttonStop.setOnClickListener {
            Thread.sleep(10 * 1000)     //  10 seconds

//            var runnable = Runnable {
//                Thread.sleep(10 * 1000)     //  10 seconds
//            }
//
//            var thread = Thread(runnable)
//            thread.start()
        }
    }
}