package com.example.unsplashphoto.data.photo


import com.google.gson.annotations.SerializedName

data class PreviewPhoto(
    @SerializedName("created_at")
    val createdAt: String,
    val id: String,
    @SerializedName("updated_at")
    val updatedAt: String,
    val urls: UrlsX
)