package com.example.unsplashphoto

import android.app.Application
import com.example.unsplashphoto.di.AppComponent
import com.example.unsplashphoto.di.AppModule
import com.example.unsplashphoto.di.DaggerAppComponent
import com.example.unsplashphoto.di.NetworkModule

class AppDelegate : Application() {

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder()
            .networkModule(NetworkModule(this))
            .appModule(AppModule(this))
            .build()

        appComponent.inject(this)
    }

    companion object {
        lateinit var appComponent: AppComponent
    }
}
