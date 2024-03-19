package com.compose.aiartcreatorcompose.repository

import kotlinx.coroutines.flow.Flow
import okhttp3.ResponseBody
import retrofit2.Response

interface ImageRepository {

    suspend fun createImage(prompt:String) : Flow<Response<ResponseBody>>
}