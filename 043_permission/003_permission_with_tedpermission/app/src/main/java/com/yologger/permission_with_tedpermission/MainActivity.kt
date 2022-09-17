package com.yologger.permission_with_tedpermission

import android.Manifest
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import com.gun0912.tedpermission.PermissionListener
import com.gun0912.tedpermission.TedPermission
import java.util.ArrayList

class MainActivity : AppCompatActivity() {

    val buttonOpenCamera: Button by lazy { findViewById<Button>(R.id.activity_main_button) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        buttonOpenCamera.setOnClickListener {
            TedPermission.with(this)
                    .setPermissions(Manifest.permission.CAMERA)
                    .setPermissionListener(object: PermissionListener {
                        override fun onPermissionGranted() {
                            Log.d("TEST", "granted")
                        }

                        override fun onPermissionDenied(deniedPermissions: ArrayList<String>?) {
                            Log.d("TEST", "denied")
                        }
                    })
                    .setDeniedMessage("If you reject permission, you can not use this service. \n\nPlease turn on permissions at [Setting] > [Permission]")
                    .check()
        }
    }
}