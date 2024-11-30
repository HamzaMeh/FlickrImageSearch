package com.example.flickrimagesearch.data.network

import com.example.flickrimagesearch.data.model.remote.FlickrResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("services/feeds/photos_public.gne")
    suspend fun searchPhotos(
        @Query("format") format: String = "json",
        @Query("nojsoncallback") noJsonCallback: Int = 1,
        @Query("tags") tags: String
    ): FlickrResponse



}