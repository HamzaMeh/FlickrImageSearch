package com.example.flickrimagesearch.domain.repository

import com.example.flickrimagesearch.data.model.remote.FlickrImage

interface FlickrRepository {

    suspend fun searchImages(query: String): List<FlickrImage>
}