package com.example.unsplashphoto.data.photo


import com.google.gson.annotations.SerializedName

data class RelatedCollections(
    val results: List<Result>,
    val total: Int,
    val type: String
)