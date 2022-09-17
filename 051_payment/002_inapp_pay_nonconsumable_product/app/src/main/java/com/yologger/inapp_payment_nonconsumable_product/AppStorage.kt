package com.yologger.inapp_payment_nonconsumable_product

import android.content.Context
import android.content.SharedPreferences

class AppStorage(context: Context) {

    private var pref: SharedPreferences = context.getSharedPreferences("storage", Context.MODE_PRIVATE)

    fun put(key: String, value: String) {
        val editor = pref.edit()
        editor.putString(key, value)
        editor.apply()
    }

    fun getString(key: String?): String? {
        return pref.getString(key, null)
    }
}
