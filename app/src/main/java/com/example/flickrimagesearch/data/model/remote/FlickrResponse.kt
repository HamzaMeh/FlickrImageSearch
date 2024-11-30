package com.example.flickrimagesearch.data.model.remote

data class FlickrResponse(
    val items: List<FlickrImage>
)

data class FlickrImage(
    val title: String,
    val link: String,
    val media: Media,
    val date_taken: String,
    val description: String,
    val published: String,
    val author: String
) {
    data class Media(
        val m: String
    )
}
