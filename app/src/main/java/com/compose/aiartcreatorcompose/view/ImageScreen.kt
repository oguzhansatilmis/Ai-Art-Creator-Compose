package com.compose.aiartcreatorcompose.view

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.compose.aiartcreatorcompose.model.Result
import com.compose.aiartcreatorcompose.viewmodel.ImageViewModel

@Composable
fun ImageScreen(viewModel: ImageViewModel) {
    val imageResponseState by viewModel.imageResponseState.collectAsState()

    Box(modifier = Modifier.fillMaxSize()) {
        when (imageResponseState) {
            is Result.Loading -> {
                // Show loading state
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center)
                )
            }
            is Result.Success -> {
                val data = (imageResponseState as Result.Success).data

            }
            is Result.Error -> {
                val errorMessage = (imageResponseState as Result.Error).message
                // Handle error state, you can access 'errorMessage' here
            }
        }
    }
}