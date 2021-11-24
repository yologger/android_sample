package com.yologger.lifecycle

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Window
import android.widget.Button

class PopUpActivity : Activity() {

    lateinit var buttonClose: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d("TEST", "[PopUpActivity] onCreate()")
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.activity_pop_up)


        buttonClose = findViewById(R.id.activity_pop_up_btn_close)

        buttonClose.setOnClickListener {
            finish()
        }
    }

    override fun onStart() {
        super.onStart()
        Log.d("TEST", "[PopUpActivity] onStart()")
    }

    override fun onResume() {
        super.onResume()
        Log.d("TEST", "[PopUpActivity] onResume()")
    }

    override fun onPause() {
        super.onPause()
        Log.d("TEST", "[PopUpActivity] onPause()")
    }

    override fun onStop() {
        super.onStop()
        Log.d("TEST", "[PopUpActivity] onStop()")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("TEST", "[PopUpActivity] onDestroy()")
    }
}