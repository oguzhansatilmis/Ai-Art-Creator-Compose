package com.compose.aiartcreatorcompose.navigation

sealed class Screen(val route :String){


    object Onboarding:Screen(route = "onboarding_screen")
    object Home:Screen(route = "home_screen")
    object CreateImage:Screen(route = "create_image_screen")
}
