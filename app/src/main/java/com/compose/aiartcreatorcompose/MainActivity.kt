package com.compose.aiartcreatorcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.compose.aiartcreatorcompose.navigation.SetupNavGraph
import com.compose.aiartcreatorcompose.view.CreateImageScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    lateinit var navController:NavHostController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            navController= rememberNavController()
             SetupNavGraph(navController)

         //   CreateImageScreen(navController = navController, prompt ="" , category =" ")

        }
    }
}
