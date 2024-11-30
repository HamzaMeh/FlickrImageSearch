package com.example.flickrimagesearch.di

import com.example.flickrimagesearch.data.network.ApiService
import com.example.flickrimagesearch.domain.repository.FlickrRepository
import com.example.flickrimagesearch.domain.repository.FlickrRepositoryImpl
import com.example.flickrimagesearch.domain.usecase.imagesearch.ImageSearchUseCase

fun getFlickrRepository(apiService: ApiService) = FlickrRepositoryImpl(apiService)

fun getSearchUseCase(flickrRepository: FlickrRepository) = ImageSearchUseCase(flickrRepository)