package com.example.unsplashphoto.data.collections

import com.google.gson.annotations.SerializedName

data class GalleryResp(
    @SerializedName("cover_photo")
    val coverPhoto: CoverPhoto,
    val description: String,
    val id: Int,
    val links: LinksXX,
    val `private`: Boolean,
    @SerializedName("published_at")
    val publishedAt: String,
    @SerializedName("share_key")
    val shareKey: String,
    val title: String,
    @SerializedName("total_photos")
    val totalPhotos: Int,
    @SerializedName("updated_at")
    val updatedAt: String,
    val user: UserX
)