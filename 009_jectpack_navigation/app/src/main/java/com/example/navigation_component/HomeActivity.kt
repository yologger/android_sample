package com.example.navigation_component

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.navigation_component.transition.TransitionActivity
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        activity_home_btn_transition.setOnClickListener {
            val intent = Intent(this, TransitionActivity::class.java)
            startActivity(intent)
        }

    }
}