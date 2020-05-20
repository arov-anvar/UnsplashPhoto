package com.example.unsplashphoto.model.repository

import com.example.unsplashphoto.UnsplashPhotoApp
import com.example.unsplashphoto.model.UnsplashApiService
import com.example.unsplashphoto.model.entity.collections.GalleryResp
import com.example.unsplashphoto.model.entity.photo.Photo
import com.example.unsplashphoto.model.entity.photos.PhotoResp
import com.example.unsplashphoto.model.entity.popular.DailyResp
import com.example.unsplashphoto.model.entity.search.SearchResp
import com.example.unsplashphoto.model.entity.user.User
import javax.inject.Inject

class UnsplashRepositoryImpl @Inject constructor(private val apiService: UnsplashApiService) :
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

    override suspend fun getUserByAsync(userName: String): User =
        apiService.getUserAsync(userName).await()



}