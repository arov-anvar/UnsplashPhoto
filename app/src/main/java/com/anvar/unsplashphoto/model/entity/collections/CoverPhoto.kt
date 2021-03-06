package com.anvar.unsplashphoto.model.entity.collections

import com.google.gson.annotations.SerializedName

data class CoverPhoto(
    val color: String,
    val description: String,
    val height: Int,
    val id: String,
    @SerializedName("liked_by_user")
    val likedByUser: Boolean,
    val likes: Int,
    val links: Links,
    val urls: Urls,
    val user: User,
    val width: Int
)
