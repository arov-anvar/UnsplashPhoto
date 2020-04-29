package com.example.unsplashphoto.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.unsplashphoto.AppDelegate
import com.example.unsplashphoto.data.collections.GalleryResp
import com.example.unsplashphoto.data.photos.PhotoResp
import com.example.unsplashphoto.data.popular.DailyResp
import com.example.unsplashphoto.data.repository.UnsplashRepositoryImpl
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import javax.inject.Inject

class UnsplashViewModel(application: Application): AndroidViewModel(application) {

    @Inject
    lateinit var repository: UnsplashRepositoryImpl

    init {
        AppDelegate.appComponent.inject(this)
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


    override fun onCleared() {
        super.onCleared()
        viewModelScope.cancel()
    }
}