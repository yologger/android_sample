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
import com.google.accompanist.permissions.*
import com.yologger.jetpackcomposepermission.ui.theme.JetpackComposePermissionTheme

class MultiPermissionActivity : ComponentActivity() {
    @ExperimentalPermissionsApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val locationPermissionsState = rememberMultiplePermissionsState(permissions = listOf(
                android.Manifest.permission.ACCESS_FINE_LOCATION,
                android.Manifest.permission.ACCESS_COARSE_LOCATION
            ))

            Column(
                verticalArrangement = Arrangement.spacedBy(12.dp),
                modifier = Modifier.padding(16.dp)
            ) {
                when {
                    locationPermissionsState.allPermissionsGranted -> {
                        PermissionGrantedButton()
                    }
                    locationPermissionsState.hasBeenDeniedForever() -> {
                        PermissionDeniedButton()
                    }
                    else -> {
                        if (locationPermissionsState.shouldShowRationale) {
                            PermissionRationale(locationPermissionsState.revokedPermissions)
                        }
                        RequestPermissionButton(locationPermissionsState)
                    }
                }
            }
        }
    }

    @ExperimentalPermissionsApi
    @Composable
    private fun RequestPermissionButton(multiplePermissionsState: MultiplePermissionsState) {
        Button(onClick = {
            multiplePermissionsState.launchMultiplePermissionRequest()
        }) {
            Text( "Request ACCESS_FINE_LOCATION/ACCESS_COARSE_LOCATION permission")
        }
    }

    @ExperimentalPermissionsApi
    @Composable
    private fun PermissionRationale(revokedPermissions: List<PermissionState>) {
        val missingPermissions = revokedPermissions.joinToString { permissionState ->
            permissionState.permission.removePrefix("android.permission.")
        }
        Text("This is a rationale explaining why we need the following permissions: $missingPermissions We are displaying this because the user has denied the permission once.")
    }

    @Composable
    private fun PermissionGrantedButton() {
        Button(onClick = {
        }) {
            Text("All permissions granted.")
        }
    }

    @Composable
    private fun PermissionDeniedButton() {
        Button(onClick = {
            val nextIntent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS, Uri.fromParts("package", packageName, null))
            startActivity(nextIntent)
        }) {
            Text("Permissions denied forever - open settings.")
        }
    }
}

@ExperimentalPermissionsApi
fun MultiplePermissionsState.hasBeenDeniedForever(): Boolean {
    return this.permissionRequested && !this.shouldShowRationale
}