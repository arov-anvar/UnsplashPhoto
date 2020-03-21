package com.example.unsplashphoto.data.photo


import com.google.gson.annotations.SerializedName

data class Exif(
    val aperture: String,
    @SerializedName("exposure_time")
    val exposureTime: String,
    @SerializedName("focal_length")
    val focalLength: String,
    val iso: Int,
    val make: String,
    val model: String
)