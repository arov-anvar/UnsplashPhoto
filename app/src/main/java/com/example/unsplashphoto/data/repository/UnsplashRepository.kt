package com.example.unsplashphoto.data.repository

import com.example.unsplashphoto.data.photos.PhotoResp

interface UnsplashRepository {
    suspend fun getCollectionByIdAsync(id: Int, page: Int, perPage: Int): List<PhotoResp>
}