package com.example.flickrimagesearch.ui.screen.detail

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.flickrimagesearch.R
import com.example.flickrimagesearch.data.model.remote.FlickrImage
import com.example.flickrimagesearch.ui.components.CustomText
import com.example.flickrimagesearch.ui.components.ShareButton
import com.example.flickrimagesearch.ui.theme.Typography
import createShareIntent
import formatPublishedDate

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ImageDetailScreen(
    imageLink: String?,
    onBackClick: () -> Unit,
    image: FlickrImage? = null
) {
    val context = LocalContext.current
    val scrollState = rememberScrollState()

    Scaffold(topBar = {
        TopAppBar(
            title = {
                CustomText(
                    stringResource(R.string.image_details),
                    style = Typography.titleLarge
                )
            },
            colors = TopAppBarDefaults.topAppBarColors(containerColor = MaterialTheme.colorScheme.primary),
            navigationIcon = {
                IconButton(onClick = { onBackClick() }) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = stringResource(R.string.back)
                    )
                }
            }
        )

    }) { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(scrollState)
                    .padding(16.dp)
                    .padding(bottom = 72.dp), // Add padding at bottom for share button
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Image
                AsyncImage(
                    model = image?.media?.m ?: imageLink,
                    contentDescription = image?.title ?: stringResource(R.string.image),
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(1f),
                    contentScale = ContentScale.Fit
                )

                Spacer(modifier = Modifier.height(8.dp))

                // Title
                CustomText(
                    text = image?.title ?: stringResource(R.string.no_title),
                    style = MaterialTheme.typography.titleLarge
                )

                Spacer(modifier = Modifier.height(8.dp))

                // Description
                CustomText(
                    text = image?.description ?: stringResource(R.string.no_description),
                    style = MaterialTheme.typography.bodyMedium
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Author
                CustomText(
                    text = stringResource(
                        R.string.author,
                        image?.author ?: stringResource(R.string.unknown)
                    ),
                    style = MaterialTheme.typography.bodyMedium
                )

                Spacer(modifier = Modifier.height(8.dp))

                // Published Date
                image?.published?.let { publishedDate ->
                    CustomText(
                        text = "Published: ${formatPublishedDate(publishedDate)}",
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

            }
        }

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            contentAlignment = Alignment.BottomCenter
        ) {
            ShareButton(
                text = stringResource(R.string.share_image),
                onShare = {
                    val shareIntent = createShareIntent(image)
                    context.startActivity(shareIntent)
                },
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}
