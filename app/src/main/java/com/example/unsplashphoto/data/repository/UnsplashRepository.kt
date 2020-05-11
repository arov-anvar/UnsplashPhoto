package com.example.unsplashphoto.data.repository

import com.example.unsplashphoto.data.model.collections.GalleryResp
import com.example.unsplashphoto.data.model.photo.Photo
import com.example.unsplashphoto.data.model.photos.PhotoResp
import com.example.unsplashphoto.data.model.popular.DailyResp
import com.example.unsplashphoto.data.model.search.SearchResp
import com.example.unsplashphoto.data.model.user.User

interface UnsplashRepository {

    suspend fun getCollectionByIdAsync(id: Int, page: Int, perPage: Int): List<PhotoResp>
    suspend fun getCollectionsAsync(page: Int, perPage: Int): List<GalleryResp>
    suspend fun getMostPopularPicture(): List<DailyResp>
    suspend fun searchPhotoAsync(query: String, page: Int): SearchResp
    suspend fun getPhotoByIdAsync(id: String): Photo
    suspend fun getUserByAsync(userName: String): User

}