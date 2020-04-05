package com.example.unsplashphoto.data.repository

import com.example.unsplashphoto.AppDelegate
import com.example.unsplashphoto.data.NetworkApiService
import com.example.unsplashphoto.data.photos.PhotoResp
import javax.inject.Inject

class UnsplashRepositoryImpl @Inject constructor(private val apiService: NetworkApiService) :
    UnsplashRepository {

    init {
        AppDelegate.appComponent.inject(this)
    }

    override suspend fun getCollectionByIdAsync(id: Int, page: Int, perPage: Int): List<PhotoResp> =
        apiService.getCollectionPhotosByIdAsync(id, page, perPage).await()

}