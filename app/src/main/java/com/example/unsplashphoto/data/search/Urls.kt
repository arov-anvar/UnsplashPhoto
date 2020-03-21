package com.example.unsplashphoto.data.search


import com.google.gson.annotations.SerializedName

data class Urls(
    val full: String,
    val raw: String,
    val regular: String,
    val small: String,
    val thumb: String
)