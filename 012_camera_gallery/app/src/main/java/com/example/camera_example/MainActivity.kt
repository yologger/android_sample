package com.example.camera_example

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.provider.MediaStore
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    private val TAG_OPEN_GALLERY = 1
    private val TAG_OPEN_CAMERA = 2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        activity_main_btn_change.setOnClickListener {
            val drawable = this.getDrawable(R.drawable.avatar1)
            activity_main_iv.setImageDrawable(drawable)
        }

        activity_main_btn_gallery.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK)
            intent.type = "image/*"
            val mimeTypes = arrayOf("image/jpeg", "image/png")
            intent.putExtra(Intent.EXTRA_MIME_TYPES, mimeTypes)
            startActivityForResult(intent, TAG_OPEN_GALLERY)
        }

        activity_main_btn_camera.setOnClickListener {
            val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            if (takePictureIntent.resolveActivity(packageManager) != null) {
                startActivityForResult(takePictureIntent, TAG_OPEN_GALLERY)
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == TAG_OPEN_GALLERY) {
                if (data == null) {
                    return
                }
                val selectedImageURL = data.data
                val imageStream = contentResolver.openInputStream(selectedImageURL!!)
                val selectedImage = BitmapFactory.decodeStream(imageStream)
                activity_main_iv.setImageBitmap(selectedImage)
            } else if (requestCode == TAG_OPEN_CAMERA) {
                val imageBitmap = data?.extras?.get("data") as? Bitmap
                activity_main_iv.setImageBitmap(imageBitmap!!)
            }
        } else {
        }
    }
}