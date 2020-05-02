package com.example.unsplashphoto.data.repository

import com.example.unsplashphoto.AppDelegate
import com.example.unsplashphoto.data.NetworkApiService
import com.example.unsplashphoto.data.collections.GalleryResp
import com.example.unsplashphoto.data.photos.PhotoResp
import com.example.unsplashphoto.data.popular.DailyResp
import com.example.unsplashphoto.data.search.SearchResp
import javax.inject.Inject

class UnsplashRepositoryImpl @Inject constructor(private val apiService: NetworkApiService) :
    UnsplashRepository {

    init {
        AppDelegate.appComponent.inject(this)
    }

    override suspend fun getCollectionByIdAsync(id: Int, page: Int, perPage: Int): List<PhotoResp> =
        apiService.getCollectionPhotosByIdAsync(id, page, perPage).await()

    override suspend fun getCollectionsAsync(page: Int, perPage: Int): List<GalleryResp> =
        apiService.getCollectionsAsync(page, perPage).await()

    override suspend fun getMostPopularPicture(): List<DailyResp> =
        apiService.getMostPopularPictureAsync().await()

    override suspend fun searchPhotoAsync(query: String, page: Int): SearchResp =
        apiService.searchPhotoAsync(page, query).await()

}