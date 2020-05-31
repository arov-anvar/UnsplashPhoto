package com.example.unsplashphoto.model.entity.search.collection


import com.example.unsplashphoto.model.entity.collections.Links
import com.example.unsplashphoto.model.entity.collections.Urls
import com.example.unsplashphoto.model.entity.collections.User
import com.google.gson.annotations.SerializedName

data class CoverPhoto(
    @SerializedName("color")
    val color: String,
    @SerializedName("created_at")
    val createdAt: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("height")
    val height: Int,
    @SerializedName("id")
    val id: String,
    @SerializedName("liked_by_user")
    val likedByUser: Boolean,
    @SerializedName("likes")
    val likes: Int,
    @SerializedName("links")
    val links: Links,
    @SerializedName("urls")
    val urls: Urls,
    @SerializedName("user")
    val user: User,
    @SerializedName("width")
    val width: Int
)