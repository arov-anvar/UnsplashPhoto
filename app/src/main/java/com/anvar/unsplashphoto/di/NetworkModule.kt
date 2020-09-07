package com.anvar.unsplashphoto.di

import com.anvar.unsplashphoto.model.UnsplashApiService
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class NetworkModule {

    @Singleton
    @Provides
    fun provideUnsplashApiService() = UnsplashApiService()
}