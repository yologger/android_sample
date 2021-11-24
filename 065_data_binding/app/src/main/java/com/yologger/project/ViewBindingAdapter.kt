package com.yologger.project

import android.util.Log
import android.view.View
import androidx.databinding.BindingAdapter

object ViewBindingAdapter {

    @BindingAdapter("android:isVisible")
    @JvmStatic
    fun setVisibility(view: View, isVisible: Boolean) {
        view.visibility = if (isVisible) View.VISIBLE else View.INVISIBLE
    }
}