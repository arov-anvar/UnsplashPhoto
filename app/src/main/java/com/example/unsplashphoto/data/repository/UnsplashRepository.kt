package com.example.unsplashphoto.data.repository

import com.example.unsplashphoto.data.collections.GalleryResp
import com.example.unsplashphoto.data.photos.PhotoResp
import com.example.unsplashphoto.data.popular.DailyResp
import com.example.unsplashphoto.data.search.SearchResp

interface UnsplashRepository {

    suspend fun getCollectionByIdAsync(id: Int, page: Int, perPage: Int): List<PhotoResp>
    suspend fun getCollectionsAsync(page: Int, perPage: Int): List<GalleryResp>
    suspend fun getMostPopularPicture(): List<DailyResp>
    suspend fun searchPhotoAsync(query: String, page: Int): SearchResp

}