package com.anvar.unsplashphoto

import android.app.Application
import com.anvar.unsplashphoto.di.AppComponent
import com.anvar.unsplashphoto.di.DaggerAppComponent

class UnsplashPhotoApp : Application() {

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder().build()
    }

    companion object {
        lateinit var appComponent: AppComponent
    }
}
