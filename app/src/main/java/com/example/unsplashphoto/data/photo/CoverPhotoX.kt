package com.example.unsplashphoto.data.photo


import com.google.gson.annotations.SerializedName

data class CoverPhotoX(
    @SerializedName("alt_description")
    val altDescription: String,
    val categories: List<Any>,
    val color: String,
    @SerializedName("created_at")
    val createdAt: String,
    @SerializedName("current_user_collections")
    val currentUserCollections: List<Any>,
    val description: Any,
    val height: Int,
    val id: String,
    @SerializedName("liked_by_user")
    val likedByUser: Boolean,
    val likes: Int,
    val links: LinksXXXX,
    @SerializedName("promoted_at")
    val promotedAt: String,
    @SerializedName("updated_at")
    val updatedAt: String,
    val urls: UrlsXX,
    val user: UserX,
    val width: Int
)