package com.yologger.dark_theme

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button

class MainActivity : AppCompatActivity() {

    private val buttonSettings: Button by lazy { findViewById(R.id.activity_main_button_button_settings) }

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d("YOLO", "MainActivity: onCreate()")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        buttonSettings.setOnClickListener {
            val nextIntent = Intent(this, SettingsActivity::class.java)
            startActivity(nextIntent)
        }
    }

    override fun onStart() {
        Log.d("YOLO", "MainActivity: onStart()")
        super.onStart()
    }

    override fun onStop() {
        Log.d("YOLO", "MainActivity: onStop()")
        super.onStop()
    }

    override fun onDestroy() {
        Log.d("YOLO", "MainActivity: onDestroy()")
        super.onDestroy()
    }
}