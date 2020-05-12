package com.example.unsplashphoto.di

import com.example.unsplashphoto.data.NetworkApiService
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class NetworkModule {

    @Singleton
    @Provides
    fun provideUnsplashApiService() = NetworkApiService()
}