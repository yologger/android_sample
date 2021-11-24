package com.example.multiple_images

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        this.initUI()
    }

    private fun initUI() {
        activity_main_btn_image_gallery.setOnClickListener {
            val nextIntent = Intent(this, SingleImageActivity::class.java)
            startActivity(nextIntent)
        }

        activity_main_btn_photo_view.setOnClickListener {
            val nextIntent = Intent(this, PhotoViewActivity::class.java)
            startActivity(nextIntent)
        }

        activity_main_btn_images_gallery.setOnClickListener {
            val nextIntent = Intent(this, MultipleImagesActivity::class.java)
            startActivity(nextIntent)
        }

        activity_main_btn_upload_image.setOnClickListener {
            val nextIntent = Intent(this, UploadImageActivity::class.java)
            startActivity(nextIntent)
        }
    }
}