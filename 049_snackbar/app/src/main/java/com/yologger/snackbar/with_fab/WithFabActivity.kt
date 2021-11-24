package com.yologger.snackbar.with_fab

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.coordinatorlayout.widget.CoordinatorLayout
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import com.yologger.snackbar.R

class WithFabActivity : AppCompatActivity() {

    private val rootView: CoordinatorLayout by lazy { findViewById(R.id.activity_with_fab_rootView) }
    // private val rootView: ConstraintLayout by lazy { findViewById(R.id.activity_with_fab_rootView) }
    private val fab: FloatingActionButton by lazy { findViewById(R.id.activity_with_fab_fab) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_with_fab)

        fab.setOnClickListener {
            val snackbar = Snackbar.make(rootView, "This is snackbar", Snackbar.LENGTH_SHORT)
            snackbar.show()
        }
    }
}