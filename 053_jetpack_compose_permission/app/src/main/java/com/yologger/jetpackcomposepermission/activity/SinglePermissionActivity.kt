package com.yologger.jetpackcomposepermission.activity

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionState
import com.google.accompanist.permissions.rememberPermissionState
import com.yologger.jetpackcomposepermission.ui.theme.JetpackComposePermissionTheme

class SinglePermissionActivity : ComponentActivity() {
    @ExperimentalPermissionsApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val cameraFineLocationPermissionState = rememberPermissionState(permission = android.Manifest.permission.CAMERA)

            Column(
                verticalArrangement = Arrangement.spacedBy(12.dp),
                modifier = Modifier.padding(16.dp)
            ) {
                if (cameraFineLocationPermissionState.shouldShowRationale) {
                    PermissionRationale()
                }

                if (cameraFineLocationPermissionState.hasPermission) {
                    PermissionGrantedButton()
                } else if (cameraFineLocationPermissionState.hasBeenDeniedForever()) {
                    PermissionDeniedButton()
                } else {
                    RequestPermissionButton(cameraFineLocationPermissionState)
                }
            }
        }
    }

    @Composable
    private fun PermissionRationale() {
        Text("This is a rationale explaining why we need the ACCESS_FINE_LOCATION permission. We are displaying this because the user has denied the permission once.")
    }

    @ExperimentalPermissionsApi
    @Composable
    private fun RequestPermissionButton(accessFineLocationPermissionState: PermissionState) {
        Button(onClick = {
            if (!accessFineLocationPermissionState.hasPermission) {
                accessFineLocationPermissionState.launchPermissionRequest()
            }
        }) {
            Text( "Request CAMERA permission")
        }
    }

    @Composable
    private fun PermissionGrantedButton() {
        Button(onClick = {
        }) {
            Text("CAMERA permission granted.")
        }
    }

    @Composable
    private fun PermissionDeniedButton() {
        Button(onClick = {
            val nextIntent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS, Uri.fromParts("package", packageName, null))
            startActivity(nextIntent)
        }) {
            Text("Camera permission denied forever - open settings.")
        }
    }
}

@ExperimentalPermissionsApi
fun PermissionState.hasBeenDeniedForever(): Boolean {
    return this.permissionRequested && !this.shouldShowRationale
}