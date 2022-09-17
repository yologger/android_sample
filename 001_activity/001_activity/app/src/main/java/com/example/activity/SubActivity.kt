package com.example.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Window
import kotlinx.android.synthetic.main.activity_sub.*

class SubActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_sub)
        Log.d("YOLO", "SubActivity: onCreate()")
        activity_sub_btn_back.setOnClickListener {
            finish()
        }
    }

    override fun onStart() {
        super.onStart()
        Log.d("YOLO", "SubActivity: onStart()")
    }

    override fun onResume() {
        super.onResume()
        Log.d("YOLO", "SubActivity: onResume()")
    }

    override fun onPause() {
        super.onPause()
        Log.d("YOLO", "SubActivity: onPause()")
    }

    override fun onStop() {
        super.onStop()
        Log.d("YOLO", "SubActivity: onStop()")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d("YOLO", "SubActivity: onRestart()")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("YOLO", "SubActivity: onDestroy()")
    }
}