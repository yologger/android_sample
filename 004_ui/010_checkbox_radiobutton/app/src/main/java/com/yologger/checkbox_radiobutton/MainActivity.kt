package com.yologger.checkbox_radiobutton

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.CheckBox
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    lateinit var radioButtonSelectAll: RadioButton
    lateinit var radioButtonClear: RadioButton
    lateinit var radioButtonSelectPartially: RadioButton
    lateinit var radioGroup: RadioGroup

    lateinit var checkBoxOption1: CheckBox
    lateinit var checkBoxOption2: CheckBox
    lateinit var checkBoxOption3: CheckBox

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        findViewsByIds()
        setupUI()
    }

    private fun findViewsByIds() {
        radioButtonSelectAll = findViewById(R.id.activity_main_rb_select_all)
        radioButtonClear = findViewById(R.id.activity_main_rb_clear)
        radioButtonSelectPartially = findViewById(R.id.activity_main_rb_select_partially)
        radioGroup = findViewById(R.id.activity_main_rg)

        checkBoxOption1 = findViewById(R.id.activity_main_cb_option1)
        checkBoxOption2 = findViewById(R.id.activity_main_cb_option2)
        checkBoxOption3 = findViewById(R.id.activity_main_cb_option3)
    }

    private fun setupUI() {
        radioGroup.setOnCheckedChangeListener { _, id ->
            when(id) {
                R.id.activity_main_rb_select_all -> {
                    checkBoxOption1.isChecked = true
                    checkBoxOption2.isChecked = true
                    checkBoxOption3.isChecked = true
                    checkBoxOption1.isClickable = false
                    checkBoxOption2.isClickable = false
                    checkBoxOption3.isClickable = false
                }
                R.id.activity_main_rb_clear -> {
                    checkBoxOption1.isChecked = false
                    checkBoxOption2.isChecked = false
                    checkBoxOption3.isChecked = false
                    checkBoxOption1.isClickable = false
                    checkBoxOption2.isClickable = false
                    checkBoxOption3.isClickable = false
                }
                R.id.activity_main_rb_select_partially -> {
                    checkBoxOption1.isClickable = true
                    checkBoxOption2.isClickable = true
                    checkBoxOption3.isClickable = true
                }
            }
        }
    }
}