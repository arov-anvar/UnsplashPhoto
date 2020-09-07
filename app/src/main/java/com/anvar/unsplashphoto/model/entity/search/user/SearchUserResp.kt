package com.anvar.unsplashphoto.model.entity.search.user


import com.google.gson.annotations.SerializedName

data class SearchUserResp(
    @SerializedName("results")
    val results: List<Result>,
    @SerializedName("total")
    val total: Int,
    @SerializedName("total_pages")
    val totalPages: Int
)