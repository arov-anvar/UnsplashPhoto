package com.anvar.unsplashphoto.ui.user

import androidx.lifecycle.*
import com.anvar.unsplashphoto.UnsplashPhotoApp
import com.anvar.unsplashphoto.model.entity.user.User
import com.anvar.unsplashphoto.model.repository.UnsplashRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

class UserViewModel : ViewModel() {

    @Inject
    lateinit var repository: UnsplashRepository

    private val pageNumber = MutableLiveData<Int>()
    private var maxCountImages = Int.MAX_VALUE
    var isLoading = false

    init {
        UnsplashPhotoApp.appComponent.inject(this)
    }

    fun getUser(userName: String): MutableLiveData<User> {
        mUserName = userName
        val userLive = MutableLiveData<User>()
        viewModelScope.launch {
            val user = repository.getUserByAsync(userName)
            maxCountImages = user.totalPhotos + 21
            userLive.value = user
        }
        pageNumber.value = 1
        return userLive
    }

    private var mUserName = ""
    val userImagesLiveData = pageNumber.switchMap { page ->
        liveData {
            isLoading = true
            emit(repository.getUserImagesByAsync(mUserName, page).map { UserImageItem(it.urls.small, it.id) })
        }
    }

    fun nextPage() {
        var page = pageNumber.value ?: 1
        if (maxCountImages > ++page * 21 && !isLoading) {
            pageNumber.value = pageNumber.value?.plus(1)
        }
    }

}