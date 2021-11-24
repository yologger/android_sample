package com.yologger.toast

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.widget.Button
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    private val default: Button by lazy { findViewById(R.id.activity_main_button_default_toast) }
    private val center: Button by lazy { findViewById(R.id.activity_main_button_center_toast) }
    private val topLeft: Button by lazy { findViewById(R.id.activity_main_button_top_left) }
    private val topLeftWithOffset: Button by lazy { findViewById(R.id.activity_main_button_top_left_with_offset) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        default.setOnClickListener {
            val toast = Toast.makeText(this@MainActivity, "default", Toast.LENGTH_SHORT)
            toast.show()
        }

        center.setOnClickListener {
            val toast = Toast.makeText(applicationContext, "Center", Toast.LENGTH_SHORT)
            toast.setGravity(Gravity.TOP, 0, 0)
            toast.show()
        }

        topLeft.setOnClickListener {
            val toast = Toast.makeText(applicationContext, "Top Left", Toast.LENGTH_SHORT)
            toast.setGravity(Gravity.TOP or Gravity.LEFT, 0, 0)
            toast.show()
        }

        topLeftWithOffset.setOnClickListener {
            val toast = Toast.makeText(applicationContext, "Top Left with Offset", Toast.LENGTH_SHORT)
            toast.setGravity(Gravity.TOP or Gravity.LEFT, 200, 500)
            toast.show()
        }
    }
}