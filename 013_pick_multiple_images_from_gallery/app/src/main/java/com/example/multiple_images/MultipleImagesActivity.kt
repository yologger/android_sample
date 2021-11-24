package com.example.multiple_images

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager.widget.ViewPager
import com.bumptech.glide.Glide
import com.google.android.material.slider.Slider
import com.ouattararomuald.slider.ImageSlider
import com.ouattararomuald.slider.SliderAdapter
import com.ouattararomuald.slider.loaders.glide.GlideImageLoaderFactory
import gun0912.tedimagepicker.builder.TedImagePicker
import kotlinx.android.synthetic.main.activity_multiple_images.*
import kotlinx.android.synthetic.main.activity_single_image.*

class MultipleImagesActivity : AppCompatActivity() {

    private lateinit var imageSlider: ImageSlider

    private val imageUrls = arrayListOf(
        "http://i.imgur.com/CqmBjo5.jpg",
        "http://i.imgur.com/zkaAooq.jpg",
        "http://i.imgur.com/0gqnEaY.jpg"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_multiple_images)
        this.initUI()
    }

    private fun initUI() {

        activity_multiple_images_btn.setOnClickListener {
            TedImagePicker.with(this)
                .startMultiImage { uris ->
                    var uriStrings = uris.map { it.toString() }

                    activity_multiple_images_is.adapter = SliderAdapter(
                        this,
                        GlideImageLoaderFactory(),
                        imageUrls = uriStrings
                    )
                }
        }

//        activity_multiple_images_is.setOnPageChangeListener(object: ViewPager.OnPageChangeListener {
//            override fun onPageScrollStateChanged(state: Int) {
//                return
//            }
//
//            override fun onPageScrolled(
//                position: Int,
//                positionOffset: Float,
//                positionOffsetPixels: Int
//            ) {
//                return
//            }
//
//            override fun onPageSelected(position: Int) {
//                println("PAGE: ${position}")
//            }
//
//        })

//        TedImagePicker.with(this)
//            .startMultiImage { imageUris ->
//
//            }
    }
}