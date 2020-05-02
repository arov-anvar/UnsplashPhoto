package com.example.unsplashphoto.ui

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.example.unsplashphoto.R
import com.example.unsplashphoto.data.collections.GalleryResp
import com.example.unsplashphoto.data.photos.PhotoResp
import com.example.unsplashphoto.data.search.Result
import com.example.unsplashphoto.data.search.SearchResp
import com.example.unsplashphoto.ui.collection.CurrentItem
import com.example.unsplashphoto.ui.gallery.adapter.Collection
import com.example.unsplashphoto.ui.search.Photo

fun ImageView.loadImage(url : String) {
    Glide.with(this)
        .load(url)
        .placeholder(R.drawable.space)
        .centerCrop()
        .into(this)
}


fun List<GalleryResp>.mapToGalleryItem(): MutableList<Collection> {
    val result = mutableListOf<Collection>()
    this.forEach {
        result.add(
            Collection(
                id = it.id,
                photo = it.coverPhoto.urls.raw,
                title = it.title,
                itemsCount = it.totalPhotos)
        )
    }

    return result
}

fun List<PhotoResp>.mapToCollectionItems(): MutableList<CurrentItem> {
    val result = mutableListOf<CurrentItem>()
    this.forEach {
        result.add(
            CurrentItem(
                id = it.id,
                photoUrl = it.urls.regular
            ))
    }

    return result
}

fun List<Result>.mapToPhotos(): MutableList<Photo>  {
    val result = mutableListOf<Photo>()
    this.forEach {
        result.add(Photo(it.urls.full))
    }

    return result
}