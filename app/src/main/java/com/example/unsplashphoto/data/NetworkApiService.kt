package com.example.unsplashphoto.data

import com.example.unsplashphoto.BuildConfig
import com.example.unsplashphoto.data.model.collections.GalleryResp
import com.example.unsplashphoto.data.model.photo.Photo
import com.example.unsplashphoto.data.model.photos.PhotoResp
import com.example.unsplashphoto.data.model.popular.DailyResp
import com.example.unsplashphoto.data.model.search.SearchResp
import com.example.unsplashphoto.data.model.user.User
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

interface NetworkApiService {

    companion object {
        operator fun invoke(): NetworkApiService {
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
                .create(NetworkApiService::class.java)
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
    ): Deferred<SearchResp>

    @GET("/photos/{id}")
    fun getPhotoAsync(
        @Path("id") id: String
    ): Deferred<Photo>

    @GET("users/{username}")
    fun getUserAsync(
        @Path("username") userName: String
    ): Deferred<User>
}