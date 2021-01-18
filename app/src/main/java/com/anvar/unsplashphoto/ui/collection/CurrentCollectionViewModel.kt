package com.anvar.unsplashphoto.ui.collection

import android.app.Application
import androidx.lifecycle.*
import com.anvar.unsplashphoto.UnsplashPhotoApp
import com.anvar.unsplashphoto.model.repository.UnsplashRepository
import javax.inject.Inject

class CurrentCollectionViewModel(application: Application) : AndroidViewModel(application) {

    @Inject
    lateinit var repository: UnsplashRepository

    init {
        UnsplashPhotoApp.appComponent.inject(this)
    }

    private var isLoading = false
    private val pageNumber = MutableLiveData<Int>()

    var mIdCollection = 0
        set(value) {
            pageNumber.value = 1
            field = value
        }

    val collection = pageNumber.switchMap {
        liveData {
            emit(repository.getCollectionByIdAsync(mIdCollection, it))
        }
    }

    fun nexPage() {
        if (!isLoading) {
            pageNumber.value = pageNumber.value?.plus(1)
        }
    }
}