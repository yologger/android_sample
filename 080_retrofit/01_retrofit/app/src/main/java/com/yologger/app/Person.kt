package com.yologger.app

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Person (
    @SerializedName("name") val name: String,
    @SerializedName("id") val id: Long,
    @Expose @SerializedName("access_token") val accessToken: String? = null
)
