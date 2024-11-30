package com.example.flickrimagesearch.ui.navigation

sealed class Screens (val route:String){

    data object HomeScreen:Screens("home")
    data object ImageDetailScreen:Screens("detail/{imageLink}") {
        fun createRoute(imageLink:String) = "detail/$imageLink"
    }
}