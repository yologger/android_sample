package com.yologger.app.api.post

import com.google.gson.annotations.SerializedName

data class UpdatePostRequest(
    @SerializedName("title") val title: String,
    @SerializedName("content") val content: String
)
