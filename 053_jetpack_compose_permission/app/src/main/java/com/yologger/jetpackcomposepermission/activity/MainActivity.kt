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
import com.google.accompanist.permissions.MultiplePermissionsState
import com.google.accompanist.permissions.PermissionState
import com.google.accompanist.permissions.rememberPermissionState
import com.yologger.jetpackcomposepermission.ui.theme.JetpackComposePermissionTheme

class MainActivity : ComponentActivity() {
    @ExperimentalPermissionsApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetpackComposePermissionTheme {
                Column {
                    Button(onClick = {
                        startActivity(Intent(this@MainActivity, SinglePermissionActivity::class.java))
                    }) {
                        Text(text = "Single Permission")
                    }
                    Button(onClick = {
                        startActivity(Intent(this@MainActivity, MultiPermissionActivity::class.java))
                    }) {
                        Text(text = "Multi Permission")
                    }
                }
            }
        }
    }

}