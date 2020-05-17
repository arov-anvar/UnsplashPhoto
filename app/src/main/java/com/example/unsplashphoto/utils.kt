package com.example.unsplashphoto

import android.content.ContentValues
import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.os.Build
import android.os.Environment
import android.provider.MediaStore
import android.widget.ImageView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.example.unsplashphoto.data.model.collections.GalleryResp
import com.example.unsplashphoto.data.model.photos.PhotoResp
import com.example.unsplashphoto.data.model.search.Result
import com.example.unsplashphoto.ui.collection.CurrentItem
import com.example.unsplashphoto.ui.gallery.adapter.Collection
import com.example.unsplashphoto.ui.search.adapter.Photo
import java.io.File
import java.io.IOException

fun showToast(context: Context, str: String) {
    Toast.makeText(context, str, Toast.LENGTH_SHORT).show()
}

fun ImageView.loadImage(url : String) {
    Glide.with(this)
        .load(url)
        .placeholder(R.drawable.empty)
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

fun Bitmap.saveToStorage(context: Context): String {
    val relativeLocation = Environment.DIRECTORY_PICTURES + File.pathSeparator + "PocketDeen"
    val contentValues = ContentValues().apply {
        put(MediaStore.MediaColumns.DISPLAY_NAME, System.currentTimeMillis().toString())
        put(MediaStore.MediaColumns.MIME_TYPE, "image/jpeg")
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            put(MediaStore.MediaColumns.RELATIVE_PATH, relativeLocation)
            put(MediaStore.MediaColumns.IS_PENDING, 1)
        }
    }

    val resolver = context.contentResolver
    val uri = resolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues)

    try {

        uri?.let { uri ->
            val stream = resolver.openOutputStream(uri)

            stream?.let { stream ->
                if (!this.compress(Bitmap.CompressFormat.JPEG, 80, stream)) {
                    throw IOException("Failed to save bitmap.")
                }
            } ?: throw IOException("Failed to get output stream.")

        } ?: throw IOException("Failed to create new MediaStore record")

    } catch (e: IOException) {
        if (uri != null) {
            resolver.delete(uri, null, null)
        }
        throw IOException(e)
    } finally {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q)
            contentValues.put(MediaStore.MediaColumns.IS_PENDING, 0)
    }

    return uri.toString()
}

fun ImageView.toBitmap(): Bitmap = (this.drawable as BitmapDrawable).bitmap

