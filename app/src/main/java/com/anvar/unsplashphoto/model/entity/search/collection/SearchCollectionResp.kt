package com.anvar.unsplashphoto.model.entity.search.collection


import com.google.gson.annotations.SerializedName

data class SearchCollectionResp(
    @SerializedName("results")
    val results: List<Result>,
    @SerializedName("total")
    val total: Int,
    @SerializedName("total_pages")
    val totalPages: Int
)