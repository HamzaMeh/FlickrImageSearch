package com.example.flickrimagesearch.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.example.flickrimagesearch.R
import com.example.flickrimagesearch.common.FlickrOutcome
import com.example.flickrimagesearch.data.model.remote.FlickrImage
import com.example.flickrimagesearch.ui.screen.home.ImageGrid

@Composable
fun CustomSearchBar(
    hint: String,
    onSearch: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    var searchQuery by rememberSaveable { mutableStateOf("") }

    TextField(
        value = searchQuery,
        onValueChange = {
            searchQuery = it
            onSearch(it)
        },
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = "Search"
            )
        },
        placeholder = { CustomText(hint, modifier = modifier) },
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Done
        ),
        keyboardActions = KeyboardActions(
            onDone = {
                onSearch(searchQuery)
            }
        ),
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp)
    )
}


@Composable
fun SearchResultHandler(
    searchResult: FlickrOutcome<List<FlickrImage>>,
    onImageClick: (FlickrImage) -> Unit,
    modifier: Modifier = Modifier
) {
    when (val result = searchResult) {
        is FlickrOutcome.Loading -> {
            Box(
                modifier = modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }

        is FlickrOutcome.Success -> {
            ImageGrid(
                images = result.data,
                onImageClick = onImageClick,
                modifier = modifier
            )
        }

        is FlickrOutcome.Error -> {
            Box(
                modifier = modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CustomText(
                    "Error: ${result.exception.message ?: "Unknown error occurred"}",
                    modifier = modifier
                )
            }
        }

        is FlickrOutcome.Empty -> {
            Box(
                modifier = modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CustomText(
                    stringResource(R.string.type_anything_to_search),
                    modifier = modifier
                )
            }
        }
    }
}