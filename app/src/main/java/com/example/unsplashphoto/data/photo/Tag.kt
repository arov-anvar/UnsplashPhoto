package com.example.unsplashphoto.data.photo


import com.google.gson.annotations.SerializedName

data class Tag(
    val source: Source,
    val title: String,
    val type: String
)