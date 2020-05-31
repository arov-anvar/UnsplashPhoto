package com.example.unsplashphoto.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.unsplashphoto.UnsplashPhotoApp
import com.example.unsplashphoto.model.entity.collections.GalleryResp
import com.example.unsplashphoto.model.entity.photo.Photo
import com.example.unsplashphoto.model.entity.photos.PhotoResp
import com.example.unsplashphoto.model.entity.popular.DailyResp
import com.example.unsplashphoto.model.entity.search.Search
import com.example.unsplashphoto.model.repository.UnsplashRepositoryImpl
import com.example.unsplashphoto.model.entity.search.SearchResp
import com.example.unsplashphoto.model.entity.user.User
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import javax.inject.Inject

class UnsplashViewModel(application: Application): AndroidViewModel(application) {

    @Inject
    lateinit var repository: UnsplashRepositoryImpl

    private val PHOTO_SEARCH = 1

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

    fun search(typeSearch: Int, query: String, page: Int): MutableLiveData<List<Search>>{
        val searchLiveData = MutableLiveData<List<Search>>()
        viewModelScope.launch {
            when (typeSearch) {
                1 -> searchLiveData.value = parseSearchPhoto(repository.searchPhotoAsync(query, page))
                2 -> searchLiveData.value = parseSearchPhoto(repository.searchPhotoAsync(query, page))
                3 -> searchLiveData.value = parseSearchPhoto(repository.searchPhotoAsync(query, page))
            }
        }
        return searchLiveData
    }

    private fun parseSearchPhoto(data: SearchResp): List<Search> {
        val listSearch = mutableListOf<Search>()
        for (value in data.results) {
            listSearch.add(Search(value.id, value.urls.full, PHOTO_SEARCH))
        }
        return listSearch
    }

    fun getPhoto(id: String): MutableLiveData<Photo> {
        val photoLive = MutableLiveData<Photo>()
        viewModelScope.launch {
            val photo = repository.getPhotoByIdAsync(id)
            photoLive.value = photo
        }

        return photoLive
    }

    fun getUser(userName: String): MutableLiveData<User> {
        val userLive = MutableLiveData<User>()
        viewModelScope.launch {
            val user = repository.getUserByAsync(userName)
            userLive.value = user
        }
        return userLive
    }


    override fun onCleared() {
        super.onCleared()
        viewModelScope.cancel()
    }
}