package com.compose.aiartcreatorcompose.network

import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.Header
import retrofit2.http.POST

interface ApiService {

    @POST("text2image")
    @FormUrlEncoded
    suspend fun getTextToImageAsStream (
        @Header("X-RapidAPI-Key") apiKey: String,
        @Field("prompt") prompt: String,
        @Field("guidance") guidance: String,
        @Field("steps") steps: Int,
        @Field("sampler") sampler: String,
        @Field("upscale") upscale: Int,
        @Field("negative_prompt") negativePrompt: String,
        @Field("model") model: String
    ): Response<ResponseBody>
}