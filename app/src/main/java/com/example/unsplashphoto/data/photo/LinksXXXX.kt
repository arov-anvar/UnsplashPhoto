package com.example.unsplashphoto.data.photo


import com.google.gson.annotations.SerializedName

data class LinksXXXX(
    val download: String,
    @SerializedName("download_location")
    val downloadLocation: String,
    val html: String,
    val self: String
)