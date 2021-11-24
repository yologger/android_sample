package com.yologger.explicit_intent

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast

class SubActivity : AppCompatActivity() {

    val button: Button by lazy { findViewById<Button>(R.id.activity_sub_button) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sub)

        var extras = intent.extras
        extras?.getString("name")?.run {
            Toast.makeText(this@SubActivity, this, Toast.LENGTH_SHORT).show()
        }

        button.setOnClickListener {
            val intent = Intent()
            intent.putExtra("nation", "England")
            setResult(Activity.RESULT_OK, intent)
            finish()
        }
    }
}