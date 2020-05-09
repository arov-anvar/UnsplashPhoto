package com.example.unsplashphoto.data.repository

import com.example.unsplashphoto.UnsplashPhotoApp
import com.example.unsplashphoto.data.NetworkApiService
import com.example.unsplashphoto.data.model.collections.GalleryResp
import com.example.unsplashphoto.data.model.photo.Photo
import com.example.unsplashphoto.data.model.photos.PhotoResp
import com.example.unsplashphoto.data.model.popular.DailyResp
import com.example.unsplashphoto.data.model.search.SearchResp
import javax.inject.Inject

class UnsplashRepositoryImpl @Inject constructor(private val apiService: NetworkApiService) :
    UnsplashRepository {

    init {
        UnsplashPhotoApp.appComponent.inject(this)
    }

    override suspend fun getCollectionByIdAsync(id: Int, page: Int, perPage: Int): List<PhotoResp> =
        apiService.getCollectionPhotosByIdAsync(id, page, perPage).await()

    override suspend fun getCollectionsAsync(page: Int, perPage: Int): List<GalleryResp> =
        apiService.getCollectionsAsync(page, perPage).await()

    override suspend fun getMostPopularPicture(): List<DailyResp> =
        apiService.getMostPopularPictureAsync().await()

    override suspend fun searchPhotoAsync(query: String, page: Int): SearchResp =
        apiService.searchPhotoAsync(page, query).await()

    override suspend fun getPhotoByIdAsync(id: String): Photo =
        apiService.getPhotoAsync(id).await()


}