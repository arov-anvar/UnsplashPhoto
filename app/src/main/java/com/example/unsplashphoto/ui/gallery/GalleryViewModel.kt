package com.example.unsplashphoto.ui.gallery

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.unsplashphoto.AppDelegate
import com.example.unsplashphoto.data.NetworkApiService
import com.example.unsplashphoto.data.collections.CollectionResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import javax.inject.Inject

class GalleryViewModel : ViewModel() {
    private val viewModelJob = SupervisorJob()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)
    private val galleryItem = MutableLiveData<List<CollectionResponse>>()

    @Inject
    lateinit var apiService: NetworkApiService

    init {
        AppDelegate.appComponent.inject(this)
    }

    private suspend fun getItemsFromNetwork(): List<CollectionResponse> {
        return apiService.getCollectionsAsync().await()
    }

    fun getItems(): MutableLiveData<List<CollectionResponse>> {
        uiScope.launch {
            val result = getItemsFromNetwork()
            galleryItem.value = result
        }

        return galleryItem
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}
