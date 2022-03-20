package com.yologger.project.presentation.join

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.yologger.project.R

class JoinActivity : AppCompatActivity() {
    private val buttonLogin: Button by lazy { findViewById(R.id.activity_join_btn_login) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_join)
        buttonLogin.setOnClickListener {
            finish()
        }
    }
}