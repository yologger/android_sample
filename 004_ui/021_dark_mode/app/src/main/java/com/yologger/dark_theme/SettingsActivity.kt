package com.yologger.dark_theme

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatDelegate

class SettingsActivity : AppCompatActivity() {

    private val buttonDark: Button by lazy { findViewById(R.id.activity_settings_button_button_dark) }
    private val buttonLight: Button by lazy { findViewById(R.id.activity_settings_button_button_light) }

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d("YOLO", "SettingsActivity: onCreate()")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        buttonDark.setOnClickListener {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        }

        buttonLight.setOnClickListener {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }
    }

    override fun onStart() {
        Log.d("YOLO", "SettingsActivity: onStart()")
        super.onStart()
    }

    override fun onStop() {
        Log.d("YOLO", "SettingsActivity: onStop()")
        super.onStop()
    }

    override fun onDestroy() {
        Log.d("YOLO", "SettingsActivity: onDestroy()")
        super.onDestroy()
    }
}