package com.example.flickrimagesearch.ui.screen.home.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.flickrimagesearch.common.FlickrOutcome
import com.example.flickrimagesearch.data.model.remote.FlickrImage
import com.example.flickrimagesearch.domain.repository.FlickrRepository
import com.example.flickrimagesearch.domain.usecase.imagesearch.ImageSearchUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class FlickrViewModel(private val imageSearchUseCase: ImageSearchUseCase) : ViewModel() {

    private val _searchResult = MutableStateFlow<FlickrOutcome<List<FlickrImage>>>(FlickrOutcome.Empty)
    val searchResult = _searchResult.asStateFlow()

    fun searchImages(query: String) {
        viewModelScope.launch {
            _searchResult.value = FlickrOutcome.Loading
            _searchResult.value = imageSearchUseCase.execute(query)
        }
    }

}