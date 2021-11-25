package com.yologger.project

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class BasicActivity : AppCompatActivity() {

    lateinit var buttonBack: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_basic)
        buttonBack = findViewById(R.id.activity_basic_btn_back)
        buttonBack.setOnClickListener { finish() }
    }
}