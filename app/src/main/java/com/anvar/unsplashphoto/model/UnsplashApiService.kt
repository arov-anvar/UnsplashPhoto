package com.anvar.unsplashphoto.model

import com.anvar.unsplashphoto.BuildConfig
import com.anvar.unsplashphoto.model.entity.collections.GalleryResp
import com.anvar.unsplashphoto.model.entity.photo.Photo
import com.anvar.unsplashphoto.model.entity.photos.PhotoResp
import com.anvar.unsplashphoto.model.entity.popular.DailyResp
import com.anvar.unsplashphoto.model.entity.search.collection.SearchCollectionResp
import com.anvar.unsplashphoto.model.entity.search.photo.SearchPhotoResp
import com.anvar.unsplashphoto.model.entity.search.user.SearchUserResp
import com.anvar.unsplashphoto.model.entity.user.User
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import kotlinx.coroutines.Deferred
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface UnsplashApiService {

    companion object {
        operator fun invoke(): UnsplashApiService {
            val requestInterceptor = Interceptor {chain ->

                val url = chain.request()
                    .url
                    .newBuilder()
                    .addQueryParameter("client_id", BuildConfig.ACCESS_KEY)
                    .build()

                val req = chain.request()
                    .newBuilder()
                    .url(url)
                    .build()

                return@Interceptor chain.proceed(req)
            }

            val logging = HttpLoggingInterceptor()
            logging.level = HttpLoggingInterceptor.Level.BODY

            val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(requestInterceptor)
                .addInterceptor(logging)
                .build()

            return Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(BuildConfig.BASE_URL)
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(UnsplashApiService::class.java)
        }
    }

    @GET("/collections")
    fun getCollectionsAsync(
        @Query("page") page: Int,
        @Query("per_page") perPage: Int
    ): Deferred<List<GalleryResp>>

    @GET("/collections/{id}/photos")
    fun getCollectionPhotosByIdAsync(
        @Path("id") id: Int,
        @Query("page") page: Int,
        @Query("per_page") perPage: Int
    ): Deferred<List<PhotoResp>>

    @GET("/photos")
    fun getMostPopularPictureAsync(
        @Query("order_by") orderBy: String = "popular"
    ): Deferred<List<DailyResp>>

    @GET("/search/photos/")
    fun searchPhotoAsync(
        @Query("page") page: Int,
        @Query("query") query: String
    ): Deferred<SearchPhotoResp>

    @GET("/search/collections/")
    fun searchCollectionsAsync(
        @Query("page") page: Int,
        @Query("query") query: String
    ): Deferred<SearchCollectionResp>

    @GET("/search/users/")
    fun searchUsersAsync(
        @Query("page") page: Int,
        @Query("query") query: String
    ): Deferred<SearchUserResp>

    @GET("/photos/{id}")
    fun getPhotoAsync(
        @Path("id") id: String
    ): Deferred<Photo>

    @GET("users/{username}")
    fun getUserAsync(
        @Path("username") userName: String
    ): Deferred<User>
}