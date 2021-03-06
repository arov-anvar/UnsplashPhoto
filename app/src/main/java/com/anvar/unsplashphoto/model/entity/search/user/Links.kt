package com.anvar.unsplashphoto.model.entity.search.user


import com.google.gson.annotations.SerializedName

data class Links(
    @SerializedName("html")
    val html: String,
    @SerializedName("likes")
    val likes: String,
    @SerializedName("photos")
    val photos: String,
    @SerializedName("self")
    val self: String
)