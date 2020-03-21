package com.example.unsplashphoto.data.photo


import com.google.gson.annotations.SerializedName

data class SourceX(
    val ancestry: AncestryX,
    @SerializedName("cover_photo")
    val coverPhoto: CoverPhotoXX,
    val description: String,
    @SerializedName("meta_description")
    val metaDescription: String,
    @SerializedName("meta_title")
    val metaTitle: String,
    val subtitle: String,
    val title: String
)