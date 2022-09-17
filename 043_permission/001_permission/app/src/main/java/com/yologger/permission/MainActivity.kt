package com.yologger.permission

import android.Manifest
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {

    lateinit var buttonOpenCamera: Button

    private val REQUEST_CODE_CAMERA_PERMISSION = 1000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        buttonOpenCamera = findViewById(R.id.activity_main_btn_open_camera)
        buttonOpenCamera.setOnClickListener {
            // openCamera()
            checkPermissionAndOpenCamera()
        }
    }

    private fun checkPermissionAndOpenCamera() {
        // 권한 확인하기
        when (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)) {
            // 권한이 있을 때
            PackageManager.PERMISSION_GRANTED -> {
                Log.d("TEST", "GRANTED! checkPermissionAndOpenCamera")
                // 카메라 열기
                openCamera()
            }
            // 권한이 없을 때
            PackageManager.PERMISSION_DENIED -> {
                // 권한 요청하기
                requestPermission()
            }
        }
    }

    private fun requestPermission() {
        ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.CAMERA), REQUEST_CODE_CAMERA_PERMISSION)
    }

    private fun openCamera() {
        var intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        startActivity(intent)
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            REQUEST_CODE_CAMERA_PERMISSION -> {
                // 권한을 승인했을 경우
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // 카메라 열기
                    openCamera()
                    // 권한을 승인하지 않았을 경우
                } else {
                    val builder = AlertDialog.Builder(this)
                    builder.setTitle("title")
                    builder.setMessage("message")
                    builder.setPositiveButton("OK") { dialog, id ->
                        Log.d("TEST", "Positive Button Clicked.")
                    }
                    builder.setNegativeButton("Cancel") { dialog, id ->
                        Log.d("TEST", "Negative Button Clicked.")
                        requestPermission()
                    }
                    val dialog = builder.create()
                    dialog.show()
                }
            }
            else -> {
                // ...
            }
        }
    }
}