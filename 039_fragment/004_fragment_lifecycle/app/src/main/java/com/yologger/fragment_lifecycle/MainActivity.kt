package com.yologger.fragment_lifecycle

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {

    val buttonFragmentA: Button by lazy { findViewById<Button>(R.id.activity_main_fragment_a) }
    val buttonFragmentB: Button by lazy { findViewById<Button>(R.id.activity_main_fragment_b) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        buttonFragmentA.setOnClickListener {
            val fragmentA = FragmentA()

            supportFragmentManager
                .beginTransaction()
                .replace(R.id.activity_main_fl, fragmentA)
                .commit()
        }

        buttonFragmentB.setOnClickListener {
            val fragmentB = FragmentB()

            supportFragmentManager
                .beginTransaction()
                .replace(R.id.activity_main_fl, fragmentB)
                .commit()
        }
    }
}