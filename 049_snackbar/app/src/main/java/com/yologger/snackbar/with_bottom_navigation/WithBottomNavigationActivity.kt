package com.yologger.snackbar.with_bottom_navigation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.coordinatorlayout.widget.CoordinatorLayout
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import com.yologger.snackbar.R

class WithBottomNavigationActivity : AppCompatActivity() {

    val rootView: LinearLayout by lazy { findViewById(R.id.activity_with_bottom_navigation_button_rootView) }
    // val rootView: CoordinatorLayout by lazy { findViewById(R.id.activity_with_bottom_navigation_button_rootView) }
    val button: Button by lazy { findViewById(R.id.activity_with_bottom_navigation_button_show_snackbar) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_with_bottom_navigation)

        button.setOnClickListener {

            val snackbar = Snackbar.make(rootView, "This is snackbar", Snackbar.LENGTH_SHORT)
            snackbar.show()
        }
    }
}