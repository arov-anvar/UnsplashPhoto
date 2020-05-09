package com.example.unsplashphoto.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.unsplashphoto.UnsplashPhotoApp
import com.example.unsplashphoto.data.model.collections.GalleryResp
import com.example.unsplashphoto.data.model.photo.Photo
import com.example.unsplashphoto.data.model.photos.PhotoResp
import com.example.unsplashphoto.data.model.popular.DailyResp
import com.example.unsplashphoto.data.repository.UnsplashRepositoryImpl
import com.example.unsplashphoto.data.model.search.SearchResp
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import javax.inject.Inject

class UnsplashViewModel(application: Application): AndroidViewModel(application) {

    @Inject
    lateinit var repository: UnsplashRepositoryImpl

    init {
        UnsplashPhotoApp.appComponent.inject(this)
    }

    fun getCollection(id: Int, page: Int, perPage: Int): MutableLiveData<List<PhotoResp>> {
        val collectionLive = MutableLiveData<List<PhotoResp>>()
        viewModelScope.launch {
            val collection = repository.getCollectionByIdAsync(id, page, perPage)
            collectionLive.value = collection
        }

        return collectionLive
    }

    fun getCollections(page: Int, perPage: Int): MutableLiveData<List<GalleryResp>> {
        val collectionsLive = MutableLiveData<List<GalleryResp>>()
        viewModelScope.launch {
            val collections = repository.getCollectionsAsync(page, perPage)
            collectionsLive.value = collections
        }

        return collectionsLive
    }

    fun getPictureDay(): MutableLiveData<DailyResp> {
        val collectionLive = MutableLiveData<DailyResp>()
        viewModelScope.launch {
            val photoes = repository.getMostPopularPicture()
            photoes.sortedBy { it.likes }
            collectionLive.value = photoes[9]
        }

        return collectionLive
    }

    fun fetchPhotosByQuery(query: String, page: Int): MutableLiveData<SearchResp> {
        val searchLive = MutableLiveData<SearchResp>()
        viewModelScope.launch {
            val search = repository.searchPhotoAsync(query, page)
            searchLive.value = search
        }

        return searchLive
    }

    fun getPhoto(id: String): MutableLiveData<Photo> {
        val photoLive = MutableLiveData<Photo>()
        viewModelScope.launch {
            val photo = repository.getPhotoByIdAsync(id)
            photoLive.value = photo
        }

        return photoLive
    }


    override fun onCleared() {
        super.onCleared()
        viewModelScope.cancel()
    }
}