package com.example.unsplashphoto.data.photo


import com.google.gson.annotations.SerializedName

data class Tag(
    @SerializedName("title")
    val title: String
)