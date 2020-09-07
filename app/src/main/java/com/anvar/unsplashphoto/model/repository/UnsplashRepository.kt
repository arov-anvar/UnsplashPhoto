package com.anvar.unsplashphoto.model.repository

import com.anvar.unsplashphoto.model.entity.collections.GalleryResp
import com.anvar.unsplashphoto.model.entity.photo.Photo
import com.anvar.unsplashphoto.model.entity.photos.PhotoResp
import com.anvar.unsplashphoto.model.entity.popular.DailyResp
import com.anvar.unsplashphoto.model.entity.search.collection.SearchCollectionResp
import com.anvar.unsplashphoto.model.entity.search.photo.SearchPhotoResp
import com.anvar.unsplashphoto.model.entity.search.user.SearchUserResp
import com.anvar.unsplashphoto.model.entity.user.User

interface UnsplashRepository {

    suspend fun getCollectionByIdAsync(id: Int, page: Int, perPage: Int): List<PhotoResp>
    suspend fun getCollectionsAsync(page: Int, perPage: Int): List<GalleryResp>
    suspend fun getMostPopularPicture(): List<DailyResp>
    suspend fun searchPhotoAsync(query: String, page: Int): SearchPhotoResp
    suspend fun searchCollectionAsync(query: String, page: Int): SearchCollectionResp
    suspend fun searchUserAsync(query: String, page: Int): SearchUserResp
    suspend fun getPhotoByIdAsync(id: String): Photo
    suspend fun getUserByAsync(userName: String): User

}