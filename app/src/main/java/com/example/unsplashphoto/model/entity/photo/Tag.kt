package com.example.unsplashphoto.model.entity.photo


import com.google.gson.annotations.SerializedName

data class Tag(
    @SerializedName("title")
    val title: String
)