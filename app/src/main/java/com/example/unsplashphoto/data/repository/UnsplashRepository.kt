package com.example.unsplashphoto.data.repository

import com.example.unsplashphoto.data.collections.GalleryResp
import com.example.unsplashphoto.data.photos.PhotoResp
import com.example.unsplashphoto.data.popular.DailyResp

interface UnsplashRepository {

    suspend fun getCollectionByIdAsync(id: Int, page: Int, perPage: Int): List<PhotoResp>
    suspend fun getCollectionsAsync(page: Int, perPage: Int): List<GalleryResp>
    suspend fun getMostPopularPicture(): List<DailyResp>
}