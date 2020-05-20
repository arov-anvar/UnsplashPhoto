package com.example.unsplashphoto.di

import com.example.unsplashphoto.model.UnsplashApiService
import com.example.unsplashphoto.model.repository.UnsplashRepositoryImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule {

    @Singleton
    @Provides
    fun provideUnsplashRepository() = UnsplashRepositoryImpl(apiService = UnsplashApiService.invoke())

}