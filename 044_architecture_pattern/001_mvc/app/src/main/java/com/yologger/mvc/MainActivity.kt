package com.yologger.mvc

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    private val repository by lazy { Repository() }

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

        val initialValue = repository.get()
        textViewResult.text = initialValue.toString()

        buttonPlus.setOnClickListener {
            repository.plus()
            val newValue = repository.get()
            textViewResult.text = newValue.toString()
        }

        buttonMinus.setOnClickListener {
            repository.minus()
            val newValue = repository.get()
            textViewResult.text = newValue.toString()
        }
    }
}