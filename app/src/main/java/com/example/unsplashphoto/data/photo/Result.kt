package com.example.unsplashphoto.data.photo


import com.google.gson.annotations.SerializedName

data class Result(
    @SerializedName("cover_photo")
    val coverPhoto: CoverPhoto,
    val curated: Boolean,
    val description: Any,
    val featured: Boolean,
    val id: Int,
    val links: LinksXXX,
    @SerializedName("preview_photos")
    val previewPhotos: List<PreviewPhoto>,
    val `private`: Boolean,
    @SerializedName("published_at")
    val publishedAt: String,
    @SerializedName("share_key")
    val shareKey: String,
    val tags: List<Tag>,
    val title: String,
    @SerializedName("total_photos")
    val totalPhotos: Int,
    @SerializedName("updated_at")
    val updatedAt: String,
    val user: UserXX
)