package com.example.flickrimagesearch

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.flickrimagesearch.common.FlickrOutcome
import com.example.flickrimagesearch.di.getApiService
import com.example.flickrimagesearch.di.getFlickrRepository
import com.example.flickrimagesearch.di.getSearchUseCase
import com.example.flickrimagesearch.ui.navigation.Screens
import com.example.flickrimagesearch.ui.screen.detail.ImageDetailScreen
import com.example.flickrimagesearch.ui.screen.home.ImageSearchScreen
import com.example.flickrimagesearch.ui.theme.FlickrImageSearchTheme
import com.example.flickrimagesearch.ui.screen.home.viewmodel.FlickrViewModel
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FlickrImageSearchTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                   FlickrApp()
                }
            }
        }
    }
}



@Composable
fun FlickrApp() {
    val navController = rememberNavController()
    val viewModel = FlickrViewModel(getSearchUseCase(getFlickrRepository(getApiService())))

    NavHost(navController, startDestination = Screens.HomeScreen.route) {
        composable(Screens.HomeScreen.route) {
            ImageSearchScreen(
                viewModel = viewModel,
                onImageClick = { image ->
                    navController.navigate(
                        Screens.ImageDetailScreen.createRoute(
                            URLEncoder.encode(image.link, StandardCharsets.UTF_8.toString())
                        )
                    )
                }
            )
        }
        composable(
            route = Screens.ImageDetailScreen.route,
            arguments = listOf(
                navArgument("imageLink") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val imageLink = backStackEntry.arguments?.getString("imageLink")
            val image = viewModel.searchResult.value.let { result ->
                if (result is FlickrOutcome.Success) {
                    result.data.find { it.link == imageLink }
                } else null
            }

            ImageDetailScreen(
                imageLink = imageLink,
                image = image,
                onBackClick = { navController.navigateUp() }
            )
        }
    }
}