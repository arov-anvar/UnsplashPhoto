package com.anvar.unsplashphoto.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.anvar.unsplashphoto.UnsplashPhotoApp
import com.anvar.unsplashphoto.model.entity.collections.GalleryResp
import com.anvar.unsplashphoto.model.entity.photo.Photo
import com.anvar.unsplashphoto.model.entity.photos.PhotoResp
import com.anvar.unsplashphoto.model.entity.popular.DailyResp
import com.anvar.unsplashphoto.model.entity.search.Search
import com.anvar.unsplashphoto.model.entity.search.collection.SearchCollectionResp
import com.anvar.unsplashphoto.model.repository.UnsplashRepositoryImpl
import com.anvar.unsplashphoto.model.entity.search.photo.SearchPhotoResp
import com.anvar.unsplashphoto.model.entity.search.user.SearchUserResp
import com.anvar.unsplashphoto.model.entity.user.User
import com.anvar.unsplashphoto.model.entity.user_images.UserImages
import com.anvar.unsplashphoto.model.entity.user_images.UserImagesItem
import com.anvar.unsplashphoto.ui.user.UserImageItem
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import javax.inject.Inject

class UnsplashViewModel(application: Application): AndroidViewModel(application) {

    @Inject
    lateinit var repository: UnsplashRepositoryImpl

    private val photoSearch = 1
    private val collectionSearch = 2
    private val userSearch = 3

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

    fun search(typeSearch: Int, query: String, page: Int): MutableLiveData<List<Search>>{
        val searchLiveData = MutableLiveData<List<Search>>()
        viewModelScope.launch {
            when (typeSearch) {
                1 -> searchLiveData.value = parseSearchPhoto(repository.searchPhotoAsync(query, page))
                2 -> searchLiveData.value = parseSearchCollection(repository.searchCollectionAsync(query, page))
                3 -> searchLiveData.value = parseSearchUser(repository.searchUserAsync(query, page))
            }
        }
        return searchLiveData
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

    fun getUserImages(userName: String): MutableLiveData<List<UserImageItem>> {
        val userImagesLive = MutableLiveData<List<UserImageItem>>()
        viewModelScope.launch {
            val userImages = repository.getUserImagesByAsync(userName).toList()
            val users = mutableListOf<UserImageItem>()
            userImages.forEach {
                users.add(UserImageItem(it.urls.raw, it.id))
            }
            userImagesLive.value = users
        }
        return userImagesLive
    }


    override fun onCleared() {
        super.onCleared()
        viewModelScope.cancel()
    }


    private fun parseSearchPhoto(data: SearchPhotoResp): List<Search> {
        val listSearch = mutableListOf<Search>()
        for (value in data.results) {
            listSearch.add(Search(value.id, value.urls.full, photoSearch))
        }
        return listSearch
    }

    private fun parseSearchCollection(data: SearchCollectionResp): List<Search> {
        val listSearch = mutableListOf<Search>()
        for (value in data.results) {
            listSearch.add(Search(value.id.toString(), value.coverPhoto.urls.full, collectionSearch))
        }
        return listSearch
    }

    private fun parseSearchUser(data: SearchUserResp): List<Search> {
        val listSearch = mutableListOf<Search>()
        for (value in data.results) {
            listSearch.add(Search(value.username, value.profileImage.large, userSearch))
        }
        return listSearch
    }
}