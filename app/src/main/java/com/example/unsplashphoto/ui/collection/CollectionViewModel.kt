package com.example.unsplashphoto.ui.collection

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.unsplashphoto.AppDelegate
import com.example.unsplashphoto.data.NetworkApiService
import com.example.unsplashphoto.data.photos.PhotosResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import javax.inject.Inject

class CollectionViewModel : ViewModel() {
    private val viewModelJob = SupervisorJob()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)
    private val photoItems = MutableLiveData<List<PhotosResponse>>()

    @Inject
    lateinit var apiService: NetworkApiService

    init {
        AppDelegate.appComponent.inject(this)
    }

    private suspend fun getPhotosFromNetwork(id: Int): List<PhotosResponse> {
        return apiService.getCollectionPhotosAsyns(id).await()
    }

    fun getItem(id: Int): MutableLiveData<List<PhotosResponse>> {
        uiScope.launch {
            val result = getPhotosFromNetwork(id)
            photoItems.value = result
        }

        return photoItems
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}
