package com.anvar.unsplashphoto.model.entity.search.collection


import com.anvar.unsplashphoto.model.entity.collections.LinksXX
import com.anvar.unsplashphoto.model.entity.collections.UserX
import com.google.gson.annotations.SerializedName

data class Result(
    @SerializedName("cover_photo")
    val coverPhoto: CoverPhoto,
    @SerializedName("description")
    val description: Any,
    @SerializedName("featured")
    val featured: Boolean,
    @SerializedName("id")
    val id: Int,
    @SerializedName("links")
    val links: LinksXX,
    @SerializedName("private")
    val `private`: Boolean,
    @SerializedName("published_at")
    val publishedAt: String,
    @SerializedName("share_key")
    val shareKey: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("total_photos")
    val totalPhotos: Int,
    @SerializedName("user")
    val user: UserX
)