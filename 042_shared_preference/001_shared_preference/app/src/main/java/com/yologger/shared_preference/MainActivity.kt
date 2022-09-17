package com.yologger.shared_preference

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    private val SHARED_PREFERENCE_FILE_KEY = "shared_preference_file_key"
    private val SHARED_PREFERENCE_VALUE_COUNTER_KEY = "shared_preference_value_counter_key"

    private var repository = Repository()

    private lateinit var textViewResult: TextView
    private lateinit var buttonPlus: Button
    private lateinit var buttonMinus: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        findViewsById()
        setupUI()
    }

    private fun findViewsById(){
        textViewResult = findViewById(R.id.activity_main_textView_result)
        buttonPlus = findViewById(R.id.activity_main_button_plus)
        buttonMinus = findViewById(R.id.activity_main_button_minus)
    }

    private fun setupUI() {
        // val sharedPreference = getPreferences(Context.MODE_PRIVATE)

        textViewResult.text = sharedPreference.getInt(SHARED_PREFERENCE_VALUE_COUNTER_KEY, 0).toString()

        buttonPlus.setOnClickListener {
            val editor = getSharedPreferences(SHARED_PREFERENCE_FILE_KEY, Context.MODE_PRIVATE).edit()
            editor.p

        }
    }


//    private fun setupUI() {
//        val value = repository.get()
//        textViewResult.text = value.toString()
//
//        buttonPlus.setOnClickListener {
//            var value = repository.get()
//            value += 1
//            repository.save(value)
//            textViewResult.text = value.toString()
//        }
//
//        buttonMinus.setOnClickListener {
//            var value = repository.get()
//            value -= 1
//            repository.save(value)
//            textViewResult.text = value.toString()
//        }
//    }
}