package com.example.unsplashphoto.data.photo


import com.google.gson.annotations.SerializedName

data class Location(
    val city: Any,
    val country: Any,
    val name: Any,
    val position: Position,
    val title: Any
)