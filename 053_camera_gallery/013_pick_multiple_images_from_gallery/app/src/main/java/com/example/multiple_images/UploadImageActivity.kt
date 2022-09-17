package com.example.multiple_images

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import gun0912.tedimagepicker.builder.TedImagePicker
import kotlinx.android.synthetic.main.activity_upload_image.*
import okhttp3.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import java.io.File

class UploadImageActivity : AppCompatActivity() {

    private val BASE_URL = "http://10.0.2.2:8000"

    private lateinit var retrofitService: RetrofitService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_upload_image)
        this.initRetrofit()
        this.initUI()
    }

    private fun initRetrofit() {

        var okHttpClient = OkHttpClient.Builder()
            .build()

        var retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            // .addConverterFactory(GsonConverterFactory.create())
            .build()

        retrofitService = retrofit.create(RetrofitService::class.java)

    }

    private fun initUI() {

        activity_upload_image_single_image.setOnClickListener {

            TedImagePicker.with(this)
                .start { uri ->
                    val path = uri.path!!
                    var file = File(path)
                    var requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file)

                    var body = MultipartBody.Part.createFormData("field", file.name, requestFile)

                    var email = RequestBody.create(MediaType.parse("multipart/form-data"), "Ronaldo@gmail.com")
                    var name = RequestBody.create(MediaType.parse("multipart/form-data"), "Ronaldo")

                    retrofitService.uploadImage(body, email, name)
                        .enqueue(object : Callback<ResponseBody> {
                            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                                Log.d("YOLO", "onFailure")
                            }

                            override fun onResponse(
                                call: Call<ResponseBody>,
                                response: Response<ResponseBody>
                            ) {
                                Log.d("YOLO", "onSuccess")
                            }

                        })
                }
        }

        activity_upload_image_multiple_images.setOnClickListener {
            TedImagePicker.with(this)
                .startMultiImage { uris ->
                    var bodies = uris.map {
                        val path = it.path!!
                        var file = File(path)
                        var requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file)
                        MultipartBody.Part.createFormData("field", file.name, requestFile)
                    }
                    var email = RequestBody.create(MediaType.parse("multipart/form-data"), "Ronaldo@gmail.com")
                    var name = RequestBody.create(MediaType.parse("multipart/form-data"), "Ronaldo")

                    retrofitService.uploadImages(bodies, email, name)
                        .enqueue(object : Callback<ResponseBody> {
                            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                                Log.d("YOLO", "onFailure")
                            }

                            override fun onResponse(
                                call: Call<ResponseBody>,
                                response: Response<ResponseBody>
                            ) {
                                Log.d("YOLO", "onSuccess")
                            }

                        })
                }
        }
    }
}