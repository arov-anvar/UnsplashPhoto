package com.example.unsplashphoto.data.photo


import com.google.gson.annotations.SerializedName

data class PhotoItemResp(
    @SerializedName("alt_description")
    val altDescription: String,
    val categories: List<Any>,
    val color: String,
    @SerializedName("created_at")
    val createdAt: String,
    @SerializedName("current_user_collections")
    val currentUserCollections: List<Any>,
    val description: String,
    val downloads: Int,
    val exif: Exif,
    val height: Int,
    val id: String,
    @SerializedName("liked_by_user")
    val likedByUser: Boolean,
    val likes: Int,
    val links: Links,
    val location: Location,
    @SerializedName("promoted_at")
    val promotedAt: String,
    @SerializedName("related_collections")
    val relatedCollections: RelatedCollections,
    val tags: List<TagX>,
    @SerializedName("updated_at")
    val updatedAt: String,
    val urls: UrlsXXXX,
    val user: UserXXXX,
    val views: Int,
    val width: Int
)