package com.yologger.hilt.common

import android.util.Log
import javax.inject.Inject

class ThemeManager @Inject constructor(

) {
    fun applyDarkTheme() {
        Log.d("KKK", "applyDarkTheme() from ThemeManager")
    }

    fun applyLightTheme() {
        Log.d("KKK", "applyLightTheme() from ThemeManager")
    }
}