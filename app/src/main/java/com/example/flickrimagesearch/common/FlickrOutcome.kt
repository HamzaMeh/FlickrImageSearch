package com.example.flickrimagesearch.common

sealed class FlickrOutcome<out T> {
    data class Success<T>(val data: T) : FlickrOutcome<T>()
    data class Error(val exception: Throwable) : FlickrOutcome<Nothing>()
    data object Loading : FlickrOutcome<Nothing>()
    data object Empty : FlickrOutcome<Nothing>()
}