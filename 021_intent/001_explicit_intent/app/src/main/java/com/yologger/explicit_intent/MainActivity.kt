package com.yologger.explicit_intent

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    private val button: Button by lazy { findViewById<Button>(R.id.activity_main_button) }
    private val REQUEST_CODE = 1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button.setOnClickListener {
            val intent = Intent(this, SubActivity::class.java)
            intent.putExtra("name", "Paul")
            startActivityForResult(intent, REQUEST_CODE)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CODE) {
            if (resultCode != Activity.RESULT_OK) { return }
            data?.extras?.getString("nation")?.run {
                Toast.makeText(this@MainActivity, this, Toast.LENGTH_SHORT).show()
            }
        }
    }
}