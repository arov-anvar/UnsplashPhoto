package com.example.unsplashphoto.data.model.photo


import com.google.gson.annotations.SerializedName

data class CurrentUserCollection(
    @SerializedName("cover_photo")
    val coverPhoto: Any,
    @SerializedName("id")
    val id: Int,
    @SerializedName("published_at")
    val publishedAt: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("updated_at")
    val updatedAt: String,
    @SerializedName("user")
    val user: Any
)