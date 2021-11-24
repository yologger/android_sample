package com.example.multiple_images

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import gun0912.tedimagepicker.builder.TedImagePicker
import kotlinx.android.synthetic.main.activity_single_image.*

class SingleImageActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_single_image)
        this.initUI()
    }

    private fun initUI() {
        activity_single_image_btn.setOnClickListener {
            TedImagePicker.with(this)
                .start { uri ->
                    Glide.with(this)
                        .load(uri)
                        .into(activity_single_image_iv);
                }
        }
    }
}