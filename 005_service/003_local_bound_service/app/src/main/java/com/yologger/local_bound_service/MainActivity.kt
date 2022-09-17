package com.yologger.local_bound_service

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    lateinit var buttonStartService: Button
    lateinit var textViewTime: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        buttonStartService = findViewById(R.id.activity_main_btn_start_service)
        textViewTime = findViewById(R.id.activity_main_tv)
    }
}