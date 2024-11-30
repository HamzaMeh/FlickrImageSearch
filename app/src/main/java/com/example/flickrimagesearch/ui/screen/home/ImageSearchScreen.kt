package com.example.flickrimagesearch.ui.screen.home

import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.flickrimagesearch.R
import com.example.flickrimagesearch.data.model.remote.FlickrImage
import com.example.flickrimagesearch.di.getApiService
import com.example.flickrimagesearch.di.getFlickrRepository
import com.example.flickrimagesearch.di.getSearchUseCase
import com.example.flickrimagesearch.ui.components.CustomSearchBar
import com.example.flickrimagesearch.ui.components.CustomText
import com.example.flickrimagesearch.ui.components.SearchResultHandler
import com.example.flickrimagesearch.ui.navigation.Screens
import com.example.flickrimagesearch.ui.screen.detail.ImageDetailScreen
import com.example.flickrimagesearch.ui.screen.home.viewmodel.FlickrViewModel
import com.example.flickrimagesearch.ui.theme.Typography
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ImageSearchScreen(
    viewModel: FlickrViewModel,
    onImageClick: (FlickrImage) -> Unit
) {
    val searchResult by viewModel.searchResult.collectAsStateWithLifecycle()


    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    CustomText(
                        stringResource(R.string.home),
                        style = Typography.titleLarge
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = MaterialTheme.colorScheme.primary)
            )
        }
    ) { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
                    .scrollable(rememberScrollState(), Orientation.Vertical)
            ) {
                CustomSearchBar(
                    onSearch = { query ->
                        if (query.isNotBlank()) viewModel.searchImages(query)
                    },
                    hint = stringResource(R.string.search_images)
                )

                SearchResultHandler(
                    searchResult = searchResult,
                    onImageClick = onImageClick,
                    modifier = Modifier.weight(1f)
                )
            }
        }
    }
}
