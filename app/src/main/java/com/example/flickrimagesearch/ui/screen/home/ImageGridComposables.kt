package com.example.flickrimagesearch.ui.screen.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.flickrimagesearch.common.FlickrOutcome
import com.example.flickrimagesearch.data.model.remote.FlickrImage

@Composable
fun ImageGrid(
    images: List<FlickrImage>,
    onImageClick: (FlickrImage) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(minSize = 128.dp),
        modifier = modifier,
        contentPadding = PaddingValues(4.dp)
    ) {
        items(images) { image ->
            AsyncImage(
                model = image.media.m,
                contentDescription = image.title,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .aspectRatio(1f)
                    .padding(4.dp)
                    .clickable { onImageClick(image) }
            )
        }
    }
}
