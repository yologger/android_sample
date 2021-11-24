package com.example.activity

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.d("YOLO", "MainActivity: onCreate()")
        this.initBinding()
    }

    override fun onStart() {
        super.onStart()
        Log.d("YOLO", "MainActivity: onStart()")
    }

    override fun onResume() {
        super.onResume()
        Log.d("YOLO", "MainActivity: onResume()")
    }

    override fun onPause() {
        super.onPause()
        Log.d("YOLO", "MainActivity: onPause()")
    }

    override fun onStop() {
        super.onStop()
        Log.d("YOLO", "MainActivity: onStop()")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d("YOLO", "MainActivity: onRestart()")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("YOLO", "MainActivity: onDestroy()")
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        Log.d("YOLO", "MainActivity: onActivityResult()")
    }

    override fun onSaveInstanceState(outState: Bundle, outPersistentState: PersistableBundle) {
        super.onSaveInstanceState(outState, outPersistentState)
        Log.d("YOLO", "MainActivity: onSaveInstanceState()")
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        Log.d("YOLO", "MainActivity: onRestoreInstanceState()")
    }

    private fun initBinding() {

        activity_main_btn_goto_subactivity.setOnClickListener {
            val nextIntent = Intent(this, SubActivity::class.java)
            startActivity(nextIntent)
        }

        activity_main_btn_open_alertdialog.setOnClickListener {
            val nextIntent = Intent(this, PopUpActivity::class.java)
            startActivity(nextIntent)
        }
    }
}