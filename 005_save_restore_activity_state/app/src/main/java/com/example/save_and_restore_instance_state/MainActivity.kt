package com.example.save_and_restore_instance_state

import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val KEY = "KEY"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.d("YOLO", "onCreate()")
        if (savedInstanceState != null) {
            // activity_main_et_email.setText(savedInstanceState.getString(KEY))
        }
    }

    override fun onStart() {
        super.onStart()
        Log.d("YOLO", "onStart()")
    }

    override fun onResume() {
        super.onResume()
        Log.d("YOLO", "onResume()")
    }

    override fun onPause() {
        super.onPause()
        Log.d("YOLO", "onPause()")
    }

    override fun onStop() {
        super.onStop()
        Log.d("YOLO", "onStop()")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("YOLO", "onDestroy()")
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        Log.d("YOLO", "onSaveInstanceState()")
        // outState.putString(KEY, activity_main_et_email.text.toString())
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        Log.d("YOLO", "onRestoreInstanceState()")
    }
}