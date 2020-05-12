package com.example.unsplashphoto

import android.app.Application
import com.example.unsplashphoto.di.AppComponent
import com.example.unsplashphoto.di.DaggerAppComponent

class UnsplashPhotoApp : Application() {

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder().build()
    }

    companion object {
        lateinit var appComponent: AppComponent
    }
}
