package com.example.unsplashphoto.data.photo


import com.google.gson.annotations.SerializedName

data class Type(
    @SerializedName("pretty_slug")
    val prettySlug: String,
    val slug: String
)