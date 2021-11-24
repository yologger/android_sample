package com.yologger.alertdialog

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AlertDialog

class MainActivity : AppCompatActivity() {

    val buttonNormal: Button by lazy { findViewById(R.id.activity_main_normal) }
    val buttonWithButtons: Button by lazy { findViewById(R.id.activity_main_with_buttons) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        buttonNormal.setOnClickListener {
            val builder = AlertDialog.Builder(this@MainActivity)

            val alertDialog = builder
                .setTitle("This is title")
                .setMessage("This is message")
                .create()

            alertDialog.show()
        }

        buttonWithButtons.setOnClickListener {

            val builder = AlertDialog.Builder(this@MainActivity)

            val alertDialog = builder
                .setTitle("This is title")
                .setMessage("This is message")
                .setPositiveButton("OK") { dialog, id ->
                    Toast.makeText(this@MainActivity, "OK clicked.", Toast.LENGTH_SHORT).show();
                }
                .setNegativeButton("Cancel") { dialog, id ->
                    Toast.makeText(this@MainActivity, "Negative clicked.", Toast.LENGTH_SHORT).show();
                }
                .setNeutralButton("Neutral") { dialog, id ->
                    Toast.makeText(this@MainActivity, "Neutral clicked.", Toast.LENGTH_SHORT).show();
                }
                .create()

            alertDialog.show()
        }
    }
}