package com.example.unsplashphoto.di

import android.app.Application
import com.example.unsplashphoto.data.NetworkApiService
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class NetworkModule(private val application: Application) {
    private val context = application.applicationContext

    @Singleton
    @Provides
    fun provideUnsplashApiService() = NetworkApiService()
}