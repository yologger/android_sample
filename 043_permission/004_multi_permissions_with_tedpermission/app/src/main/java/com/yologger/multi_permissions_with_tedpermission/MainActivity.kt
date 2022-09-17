package com.yologger.multi_permissions_with_tedpermission

import android.Manifest
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.gun0912.tedpermission.PermissionListener
import com.gun0912.tedpermission.TedPermission
import java.util.ArrayList

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        TedPermission.with(this)
                .setPermissionListener(object: PermissionListener {
                    override fun onPermissionGranted() {
                        Log.d("TEST", "granted")
                    }

                    override fun onPermissionDenied(deniedPermissions: ArrayList<String>?) {
                        Log.d("TEST", "denied")
                        deniedPermissions?.let { permissions ->
                            for (permission in permissions) {

                            }
                        }
                    }
                })
                .setDeniedMessage("If you reject permission, you can not use this service. \n\nPlease turn on permissions at [Setting] > [Permission]")
                .setPermissions(Manifest.permission.CAMERA, Manifest.permission.READ_CALENDAR)
                .check()
    }
}