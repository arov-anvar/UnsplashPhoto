package com.example.unsplashphoto.data.photo


import com.google.gson.annotations.SerializedName

data class Ancestry(
    val category: Category,
    val subcategory: Subcategory,
    val type: Type
)