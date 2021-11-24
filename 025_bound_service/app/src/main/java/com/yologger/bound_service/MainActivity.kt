package com.yologger.bound_service

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {

    private val buttonLocalBoundService: Button by lazy { findViewById<Button>(R.id.activity_main_btn_local) }
    private val buttonRemoteBoundService: Button by lazy { findViewById<Button>(R.id.activity_main_btn_remote) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        buttonLocalBoundService.setOnClickListener {
            val intent = Intent(this, LocalBoundServiceActivity::class.java)
            startActivity(intent)
        }

        buttonRemoteBoundService.setOnClickListener {
            val intent = Intent(this, RemoteBoundServiceActivity::class.java)
            startActivity(intent)
        }
    }
}