package com.yologger.start_service

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.FragmentManager

class MainActivity : AppCompatActivity() {

    lateinit var buttonShowToast: Button
    lateinit var buttonStartService: Button
    lateinit var buttonStopService: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        buttonShowToast = findViewById(R.id.activity_main_show_toast)
        buttonStartService = findViewById(R.id.activity_main_start_service)
        buttonStopService = findViewById(R.id.activity_main_stop_service)

        buttonStartService.setOnClickListener {
            val intent = Intent(this, MyStartService::class.java)
            startService(intent)
        }

        buttonShowToast.setOnClickListener {
            Toast.makeText(this@MainActivity, "Toast Message", Toast.LENGTH_SHORT).show()
        }

        buttonStopService.setOnClickListener {
            val intent = Intent(this, MyStartService::class.java)
            stopService(intent)
        }


    }
}