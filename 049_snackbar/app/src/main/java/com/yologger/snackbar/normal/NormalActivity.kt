package com.yologger.snackbar.normal

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.coordinatorlayout.widget.CoordinatorLayout
import com.google.android.material.snackbar.Snackbar
import com.yologger.snackbar.R

class NormalActivity : AppCompatActivity() {

    val rootView: CoordinatorLayout by lazy { findViewById(R.id.activity_normal_rootView) }
    val button: Button by lazy { findViewById(R.id.activity_normal_button) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_normal)
        button.setOnClickListener {
            val snackbar = Snackbar.make(rootView, "This is snackbar", Snackbar.LENGTH_SHORT)
            snackbar.show()
        }
    }
}