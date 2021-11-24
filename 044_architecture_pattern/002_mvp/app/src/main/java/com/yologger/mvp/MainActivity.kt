package com.yologger.mvp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity(), MainContract.View {

    override val presenter: MainContract.Presenter by lazy { MainPresenter(this) }

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
        presenter.start()

        buttonPlus.setOnClickListener {
            presenter.plus()
        }

        buttonMinus.setOnClickListener {
            presenter.minus()
        }
    }

    override fun showResult(result: Int) {
        textViewResult.text = result.toString()
    }
}