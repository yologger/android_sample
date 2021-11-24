package com.yologger.remote_notification

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.d("TEST", "onCreate")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d("TEST", "onRestart")
    }

    override fun onStart() {
        super.onStart()
        val extras = intent.extras

        extras?.run {
            val name = getString("name")
            val nation = getString("nation")
            if (name != null && nation != null) {
                Toast.makeText(this@MainActivity, "${name} comes from ${nation}. ", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onPause() {
        super.onPause()
        Log.d("TEST", "onPause")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("TEST", "onDestroy")
    }
}