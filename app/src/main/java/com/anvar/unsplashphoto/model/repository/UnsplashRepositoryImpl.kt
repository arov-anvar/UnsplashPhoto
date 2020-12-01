package com.anvar.unsplashphoto.model.repository

import com.anvar.unsplashphoto.UnsplashPhotoApp
import com.anvar.unsplashphoto.model.UnsplashApiService
import com.anvar.unsplashphoto.model.entity.collections.GalleryResp
import com.anvar.unsplashphoto.model.entity.photo.Photo
import com.anvar.unsplashphoto.model.entity.photos.PhotoResp
import com.anvar.unsplashphoto.model.entity.popular.DailyResp
import com.anvar.unsplashphoto.model.entity.search.collection.SearchCollectionResp
import com.anvar.unsplashphoto.model.entity.search.photo.SearchPhotoResp
import com.anvar.unsplashphoto.model.entity.search.user.SearchUserResp
import com.anvar.unsplashphoto.model.entity.user.User
import com.anvar.unsplashphoto.model.entity.user_images.UserImages
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

    override suspend fun searchPhotoAsync(query: String, page: Int): SearchPhotoResp =
        apiService.searchPhotoAsync(page, query).await()

    override suspend fun searchCollectionAsync(query: String, page: Int): SearchCollectionResp =
        apiService.searchCollectionsAsync(page, query).await()

    override suspend fun searchUserAsync(query: String, page: Int): SearchUserResp =
        apiService.searchUsersAsync(page, query).await()

    override suspend fun getPhotoByIdAsync(id: String): Photo =
        apiService.getPhotoAsync(id).await()

    override suspend fun getUserByAsync(userName: String): User =
        apiService.getUserAsync(userName).await()

    override suspend fun getUserImagesByAsync(userName: String): UserImages =
        apiService.getUserImagesAsync(userName).await()



}