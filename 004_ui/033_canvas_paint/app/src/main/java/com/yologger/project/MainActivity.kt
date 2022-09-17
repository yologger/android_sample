package com.yologger.project

import android.graphics.*
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
            strokeWidth = 5F
        }

//        val path = Path().apply {
//            // 시작점 설정
//            moveTo(100F, 100F)
//
//            // 경로 이동
//            lineTo(100F, 500F)
//            lineTo(500F, 300F)
//        }
//
//        canvas.drawPath(path, paint)

    }
}