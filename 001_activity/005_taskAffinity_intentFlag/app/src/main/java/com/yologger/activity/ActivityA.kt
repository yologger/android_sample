package com.yologger.activity

import android.app.Activity
import android.content.Intent
import android.content.Intent.*
import android.content.UriMatcher
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast

class ActivityA : AppCompatActivity() {

    private val button: Button by lazy { findViewById<Button>(R.id.activity_a_button_open_b) }

    private val REQUEST_CODE = 1000


    // https://www.example.com/person/programmer?name=paul&nation=england

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_a)

        val uriMatcher = UriMatcher(UriMatcher.NO_MATCH)

        uriMatcher.addURI("www.example.com", "person", 1)
        uriMatcher.addURI("www.example.com", "person/*", 2)
        uriMatcher.addURI("www.example.com", "car/*", 3)

        val uri1 = Uri.Builder()
            .scheme("https")
            .authority("www.example.com")
            .appendPath("person")
            .build()


        val uri2 = Uri.Builder()
            .scheme("https")
            .authority("www.example.com")
            .appendPath("person")
            .appendPath("programmer")
            .build()


        val uri3 = Uri.Builder()
            .scheme("https")
            .authority("www.example.com")
            .appendPath("car")
            .appendPath("convertible")
            .build()

        val uri4 = Uri.Builder()
            .scheme("https")
            .authority("www.test.com")
            .appendPath("book")
            .build()



        Log.d("TEST", "result: ${uriMatcher.match(uri1)}")
        Log.d("TEST", "result: ${uriMatcher.match(uri2)}")
        Log.d("TEST", "result: ${uriMatcher.match(uri3)}")
        Log.d("TEST", "result: ${uriMatcher.match(uri4)}")


    }
}