package com.yologger.lifecycle

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button

class SubActivity : AppCompatActivity() {

    lateinit var buttonClose: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d("TEST", "[SubActivity] onCreate()")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sub)

        buttonClose = findViewById(R.id.activity_sub_btn_close)

        buttonClose.setOnClickListener {
            finish()
        }
    }

    override fun onStart() {
        super.onStart()
        Log.d("TEST", "[SubActivity] onStart()")
    }

    override fun onResume() {
        super.onResume()
        Log.d("TEST", "[SubActivity] onResume()")
    }

    override fun onPause() {
        super.onPause()
        Log.d("TEST", "[SubActivity] onPause()")
    }

    override fun onStop() {
        super.onStop()
        Log.d("TEST", "[SubActivity] onStop()")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("TEST", "[SubActivity] onDestroy()")
    }
}