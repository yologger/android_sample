package com.yologger.activity

import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_FORWARD_RESULT
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class ActivityB : AppCompatActivity() {

    private val buttonOpenC: Button by lazy { findViewById<Button>(R.id.activity_b_button_open_c) }
    private val buttonBackToA: Button by lazy { findViewById<Button>(R.id.activity_b_button_back_to_a) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_b)

        buttonOpenC.setOnClickListener {
            val intent = Intent(this@ActivityB, ActivityC::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_MULTIPLE_TASK)
            startActivity(intent)
        }

        buttonBackToA.setOnClickListener {
            finish()
        }
    }
}