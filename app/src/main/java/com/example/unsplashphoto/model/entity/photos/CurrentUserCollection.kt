package com.example.unsplashphoto.model.entity.photos

import com.google.gson.annotations.SerializedName

data class CurrentUserCollection(
    @SerializedName("cover_photo")
    val coverPhoto: Any,
    val id: Int,
    @SerializedName("published_at")
    val publishedAt: String,
    val title: String,
    @SerializedName("updated_at")
    val updatedAt: String,
    val user: Any
)
