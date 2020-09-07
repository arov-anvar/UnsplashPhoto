package com.anvar.unsplashphoto.model.entity.photos

import com.google.gson.annotations.SerializedName

data class User(
    val bio: String,
    val id: String,
    @SerializedName("instagram_username")
    val instagramUsername: String,
    val links: LinksX,
    val location: String,
    val name: String,
    @SerializedName("portfolio_url")
    val portfolioUrl: String,
    @SerializedName("profile_image")
    val profileImage: ProfileImage,
    @SerializedName("total_collections")
    val totalCollections: Int,
    @SerializedName("total_likes")
    val totalLikes: Int,
    @SerializedName("total_photos")
    val totalPhotos: Int,
    @SerializedName("twitter_username")
    val twitterUsername: String,
    val username: String
)
