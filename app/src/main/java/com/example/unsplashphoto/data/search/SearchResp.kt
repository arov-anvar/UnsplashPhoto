package com.example.unsplashphoto.data.search


import com.google.gson.annotations.SerializedName

data class SearchResp(
    val results: List<Result>,
    val total: Int,
    @SerializedName("total_pages")
    val totalPages: Int
)