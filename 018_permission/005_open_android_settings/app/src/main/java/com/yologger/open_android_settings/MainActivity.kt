package com.yologger.open_android_settings

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button

class MainActivity : AppCompatActivity() {

    private val buttonOpenSettings: Button by lazy { findViewById<Button>(R.id.activity_main_btn_open_settings) }
    private val REQUEST_CODE = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        buttonOpenSettings.setOnClickListener {
            // val intent = Intent(android.provider.Settings.ACTION_SETTINGS)
            // val intent = Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS)
            // val intent = Intent(android.provider.Settings.ACTION_APPLICATION_SETTINGS)
             val intent = Intent(android.provider.Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
             intent.data = Uri.parse("package:${packageName}")
            startActivityForResult(intent, REQUEST_CODE)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when(requestCode) {
            REQUEST_CODE -> {
                if (resultCode != Activity.RESULT_OK) {

                }
            }
        }
    }
}
