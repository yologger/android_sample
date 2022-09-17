package com.yologger.activity

import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_CLEAR_TOP
import android.content.Intent.FLAG_ACTIVITY_REORDER_TO_FRONT
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class ActivityD : AppCompatActivity() {

    val button: Button by lazy { findViewById<Button>(R.id.activity_d_button_open_b) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_d)

        button.setOnClickListener {
            val intent = Intent(this@ActivityD, ActivityB::class.java)
            intent.flags = FLAG_ACTIVITY_REORDER_TO_FRONT
            startActivity(intent)
        }
    }
}
