package com.example.recycler_view.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.recycler_view.R
import com.example.recycler_view.activity.recyclerview.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        this.initUI()
    }

    private fun initUI() {

        activity_main_btn_open_scroll_view.setOnClickListener {
            val nextIntent = Intent(this, ScrollViewActivity::class.java)
            startActivity(nextIntent)
        }

        activity_main_btn_open_recycler_view.setOnClickListener {
            val nextIntent = Intent(this, RecyclerViewActivity::class.java)
            startActivity(nextIntent)
        }

        activity_main_btn_open_grid_view.setOnClickListener {
            val nextIntent = Intent(this, GridViewActivity::class.java)
            startActivity(nextIntent)
        }
    }
}