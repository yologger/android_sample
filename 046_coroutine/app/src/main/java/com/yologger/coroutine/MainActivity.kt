package com.yologger.coroutine

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import java.lang.Exception
import kotlin.coroutines.CoroutineContext

class MainActivity : AppCompatActivity() {

    private val buttonLoadData: Button by lazy { findViewById<Button>(R.id.activity_main_button_load_data) }
    private val textViewData: TextView by lazy { findViewById<TextView>(R.id.activity_main_textView_data) }
    private val buttonShowData: Button by lazy { findViewById<Button>(R.id.activity_main_button_show_data) }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        buttonLoadData.setOnClickListener {

            val flow = flow {
                for (i: Int in 1..10) {
                    emit(i)
                    delay(1000)
                    if (i == 5) {
                        throw Exception("This is error")
                    }
                }
            }


            val scope = CoroutineScope(Dispatchers.IO)

            scope.launch {
                flow
                    .catch { e ->
                        Log.d("TEST", "error: ${e.localizedMessage}")
                    }
                    .collect {
                        withContext(Dispatchers.Main) {
                            Log.d("TEST", "value: ${it}")
                        }
                    }
            }
        }

        buttonShowData.setOnClickListener {
            Toast.makeText(this@MainActivity, "${textViewData.text}", Toast.LENGTH_SHORT).show()
        }
    }
}

