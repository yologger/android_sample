package com.example.recycler_view.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.recycler_view.R
import com.example.recycler_view.activity.scrollview.CommonScrollViewActivity
import com.example.recycler_view.activity.scrollview.NestedScrollViewActivity
import com.example.recycler_view.activity.scrollview.ScrollViewWithRecyclerViewActivity
import kotlinx.android.synthetic.main.activity_scroll_view.*

class ScrollViewActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scroll_view)

        initUI()
    }

    private fun initUI() {
        activity_scroll_view_btn_open_scroll_view.setOnClickListener {
            val nextIntent = Intent(this, CommonScrollViewActivity::class.java)
            startActivity(nextIntent)
        }

        activity_scroll_view_btn_open_scroll_view_with_recycler_view.setOnClickListener {
            val nextIntent = Intent(this, ScrollViewWithRecyclerViewActivity::class.java)
            startActivity(nextIntent)
        }

        activity_scroll_view_btn_open_nested_scroll_view.setOnClickListener {
            val nextIntent = Intent(this, NestedScrollViewActivity::class.java)
            startActivity(nextIntent)
        }
    }
}