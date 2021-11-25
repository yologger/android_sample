package com.yologger.project

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button

class MainActivity : AppCompatActivity() {

    private lateinit var loginButton: LoginButton
    private lateinit var buttonOpen: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        loginButton = findViewById(R.id.activity_main_lb)
        buttonOpen = findViewById(R.id.activity_main_btn)
        loginButton.setOnClickListener {
        }
        buttonOpen.setOnClickListener {
            val intent = Intent(this@MainActivity, BasicActivity::class.java)
            startActivity(intent)
        }
    }
}