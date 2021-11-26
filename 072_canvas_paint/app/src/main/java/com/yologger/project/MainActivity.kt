package com.yologger.project

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView

class MainActivity : AppCompatActivity() {

    private val imageView: ImageView by lazy { findViewById(R.id.activity_main_iv) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val bitmap = Bitmap.createBitmap(800, 800, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bitmap)
        canvas.drawColor(Color.GRAY)

        imageView.setImageBitmap(bitmap)

        val paint = Paint().apply {
            color = Color.BLUE
            strokeWidth = 30F
        }

        canvas.drawArc(100F, 100F, 300F, 300F, 0F, 360F, true, paint)
    }
}