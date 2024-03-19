package com.compose.aiartcreatorcompose.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.compose.aiartcreatorcompose.view.CreateImageScreen
import com.compose.aiartcreatorcompose.view.HomeScreen
import com.compose.aiartcreatorcompose.view.OnboardingScreen


@Composable
fun SetupNavGraph(navController: NavHostController) {

    NavHost(navController = navController, startDestination = Screen.Onboarding.route) {

        composable(
            route = Screen.Onboarding.route
        ) {
            OnboardingScreen(navController)
        }
        composable(
            route = Screen.Home.route,

        ) {
            HomeScreen(navController)
        }
        composable(
            route = Screen.CreateImage.route+"?prompt={prompt}&category={category}",
            arguments = listOf(
                navArgument(name = "prompt"){
                    type = NavType.StringType
                    defaultValue=""
                },
                navArgument(name = "category"){
                    type = NavType.StringType
                    defaultValue=""
                }
            )
        ) {
            val prompt = it.arguments?.getString("prompt")
            val category = it.arguments?.getString("category")

            CreateImageScreen(navController, prompt, category)
        }
    }
}