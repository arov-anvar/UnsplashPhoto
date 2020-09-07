package com.anvar.unsplashphoto.model.entity.search.photo


import com.google.gson.annotations.SerializedName

data class SearchPhotoResp(
    val results: List<Result>,
    val total: Int,
    @SerializedName("total_pages")
    val totalPages: Int
)