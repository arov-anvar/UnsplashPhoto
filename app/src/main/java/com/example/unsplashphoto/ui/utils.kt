package com.example.unsplashphoto.ui

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.example.unsplashphoto.data.collections.CollectionResponse
import com.example.unsplashphoto.ui.gallery.adapter.GalleryItem

fun ImageView.loadImage(url : String) {
    Glide.with(this)
        .load(url)
        .centerCrop()
        .into(this)
}

fun List<CollectionResponse>.mapToGalleryItem(): MutableList<GalleryItem> {
    val result = mutableListOf<GalleryItem>()
    this.forEach {
        result.add(
            GalleryItem(
                id = it.id,
                photo = it.coverPhoto.urls.raw,
                title =  it.title,
                itemCount = it.totalPhotos
            )
        )
    }

    return result
}