package com.example.flickrimagesearch.domain.usecase.imagesearch

import com.example.flickrimagesearch.common.FlickrOutcome
import com.example.flickrimagesearch.data.model.remote.FlickrImage
import com.example.flickrimagesearch.domain.repository.FlickrRepository

class ImageSearchUseCase(private val repository: FlickrRepository) {

    suspend fun execute(query: String): FlickrOutcome<List<FlickrImage>> {
        return try {
            val images = repository.searchImages(query)
            if (images.isNotEmpty()) {
                FlickrOutcome.Success(images)
            } else {
                FlickrOutcome.Error(Exception("No images found"))
            }
        } catch (e: Exception) {
            FlickrOutcome.Error(e)
        }
    }

}