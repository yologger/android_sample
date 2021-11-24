package com.yologger.project

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.yologger.project.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.activityMainTv.text = "This is TextView"
        binding.activityMainBtn.text = "This is Button"
        binding.activityMainBtn.setOnClickListener {
            Toast.makeText(this@MainActivity, "Button Clicked.", Toast.LENGTH_SHORT).show()
        }
    }
}