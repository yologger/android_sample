package com.yologger.mvi.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.yologger.mvi.R
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    lateinit var viewModel: MainViewModel

    private val progressBar: ProgressBar by lazy { findViewById(R.id.activity_main_progress_bar) }
    private val buttonPlus: Button by lazy { findViewById(R.id.activity_main_button_plus) }
    private val buttonMinus: Button by lazy { findViewById(R.id.activity_main_button_minus) }
    private val buttonClear: Button by lazy { findViewById(R.id.activity_main_button_clear) }
    private val textViewValue: TextView by lazy { findViewById(R.id.activity_main_textView_value) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupViewModel()
        setupButtons()
        observeViewModel()
    }

    private fun setupViewModel() {
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
    }

    private fun setupButtons() {
        buttonPlus.setOnClickListener {
            lifecycleScope.launch {
                viewModel.intent.send(MainIntent.PlusValue)
            }
        }

        buttonMinus.setOnClickListener {
            lifecycleScope.launch {
                viewModel.intent.send(MainIntent.MinusValue)
            }
        }

        buttonClear.setOnClickListener {
            lifecycleScope.launch {
                viewModel.intent.send(MainIntent.ClearValue)
            }
        }
    }

    private fun observeViewModel() {
        lifecycleScope.launch {
            viewModel.state.collect {
                when (it) {
                    is MainState.Idle -> {
                        textViewValue.text = "0"
                        progressBar.visibility = View.GONE
                    }
                    is MainState.Updated -> {
                        textViewValue.text = "${it.value}"
                        progressBar.visibility = View.GONE
                    }
                    is MainState.Loading -> {
                        progressBar.visibility = View.VISIBLE
                    }
                }
            }
        }
    }
}