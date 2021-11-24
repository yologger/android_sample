package com.yologger.snackbar

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.yologger.snackbar.normal.NormalActivity
import com.yologger.snackbar.swipeable.SwipeableActivity
import com.yologger.snackbar.with_bottom_navigation.WithBottomNavigationActivity
import com.yologger.snackbar.with_fab.WithFabActivity

class MainActivity : AppCompatActivity() {

    private val normal: Button by lazy { findViewById(R.id.activity_main_normal) }
    private val swipeable: Button by lazy { findViewById(R.id.activity_main_swipeable) }
    private val fab: Button by lazy { findViewById(R.id.activity_main_with_fab) }
    private val bottomNavigation: Button by lazy { findViewById(R.id.activity_main_with_bottom_navigation) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        normal.setOnClickListener {
            val intent = Intent(this@MainActivity, NormalActivity::class.java)
            startActivity(intent)
        }
        swipeable.setOnClickListener {
            val intent = Intent(this@MainActivity, SwipeableActivity::class.java)
            startActivity(intent)
        }
        fab.setOnClickListener {
            val intent = Intent(this@MainActivity, WithFabActivity::class.java)
            startActivity(intent)
        }
        bottomNavigation.setOnClickListener {
            val intent = Intent(this@MainActivity, WithBottomNavigationActivity::class.java)
            startActivity(intent)
        }
    }
}