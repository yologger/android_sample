package com.yologger.lifecycle

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AlertDialog

class MainActivity : AppCompatActivity() {

    lateinit var textViewLabel: TextView
    lateinit var buttonOpenPopUpActivity: Button
    lateinit var buttonOpenSubActivity: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d("TEST", "[MainActivity] onCreate()")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        buttonOpenPopUpActivity = findViewById(R.id.activity_main_btn_open_pop_up_activity)
        buttonOpenSubActivity = findViewById(R.id.activity_main_btn_open_sub_activity)



        buttonOpenPopUpActivity.setOnClickListener {



            val ctx = baseContext

            var application = application as App
            var result = application.url

            Log.d("TEST", result)
//            val nextIntent = Intent(this, PopUpActivity::class.java)
//            startActivity(nextIntent)
//
//            var packageName = getApplicationContext().packageName
//            var resources = getApplicationContext().resources


        }

        buttonOpenSubActivity.setOnClickListener {
            val nextIntent = Intent(this, SubActivity::class.java)
            startActivity(nextIntent)
        }
    }

    override fun onRestart() {
        super.onRestart()
        Log.d("TEST", "[MainActivity] onRestart()")
    }

    override fun onStart() {
        super.onStart()
        Log.d("TEST", "[MainActivity] onStart()")
    }

    override fun onResume() {
        super.onResume()
        Log.d("TEST", "[MainActivity] onResume()")
    }

    override fun onPause() {
        super.onPause()
        Log.d("TEST", "[MainActivity] onPause()")
    }

    override fun onStop() {
        super.onStop()
        Log.d("TEST", "[MainActivity] onStop()")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("TEST", "[MainActivity] onDestroy()")
    }


}