package com.example.unsplashphoto.model.entity.collections

import com.google.gson.annotations.SerializedName

data class UserX(
    val bio: String,
    val id: String,
    val links: LinksXXX,
    val location: String,
    val name: String,
    @SerializedName("portfolio_url")
    val portfolioUrl: String,
    @SerializedName("profile_image")
    val profileImage: ProfileImageX,
    @SerializedName("total_collections")
    val totalCollections: Int,
    @SerializedName("total_likes")
    val totalLikes: Int,
    @SerializedName("total_photos")
    val totalPhotos: Int,
    @SerializedName("updated_at")
    val updatedAt: String,
    val username: String
)
