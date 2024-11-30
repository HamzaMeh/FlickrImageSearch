package com.example.flickrimagesearch.domain.repository

import com.example.flickrimagesearch.data.model.remote.FlickrImage
import com.example.flickrimagesearch.data.network.ApiService

class FlickrRepositoryImpl(private val apiService: ApiService) : FlickrRepository {

    override suspend fun searchImages(query: String): List<FlickrImage> {
        return apiService.searchPhotos(tags = query).items
    }
}