package com.yologger.jetpackcompose.ui.screen

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.yologger.jetpackcompose.ui.theme.JetpackComposeTheme
import com.yologger.jetpackcompose.ui.theme.Purple200

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetpackComposeTheme {
                Surface(color = MaterialTheme.colors.background) {
                    MainScreen()
                }
            }
        }
    }
}

@Composable
fun MainScreen() {
    Scaffold(
        topBar = { TopAppBar(title = {Text("TopAppBar")})  },
        floatingActionButtonPosition = FabPosition.End,
        floatingActionButton = { FloatingActionButton(onClick = {}){ Text("X") } },
        drawerContent = { Text(text = "drawerContent") },
        content = { MainContent() },
        bottomBar = { BottomAppBar() { Text("BottomAppBar") } }
    )
}

@Composable
fun MainContent() {
    Column(
        modifier = Modifier.width(500.dp).background(Color.Yellow)
    ) {
        val emailState = remember { mutableStateOf("") }
        val passwordState = remember { mutableStateOf("") }
        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = emailState.value,
            onValueChange = { emailState.value = it }
        )
        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = { /* Do something! */ },
            colors = ButtonDefaults.textButtonColors(backgroundColor = Purple200)
        ) {
            Text("Button")
        }
        Spacer(modifier = Modifier.size(50.dp))
        TextField(
            value = passwordState.value,
            onValueChange = { passwordState.value = it }
        )
        Button(
            onClick = { /* Do something! */ },
            colors = ButtonDefaults.textButtonColors(backgroundColor = Purple200)
        ) {
            Text("Button")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    JetpackComposeTheme {
        MainScreen()
    }
}