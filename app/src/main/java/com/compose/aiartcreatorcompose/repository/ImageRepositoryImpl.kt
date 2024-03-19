package com.compose.aiartcreatorcompose.repository

import android.media.Image
import android.util.Log
import com.compose.aiartcreatorcompose.network.ApiService
import com.compose.aiartcreatorcompose.utils.Constants
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import okhttp3.ResponseBody
import retrofit2.Response
import javax.inject.Inject

class ImageRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
) :ImageRepository{
    override suspend fun createImage(prompt: String): Flow<Response<ResponseBody>> {

        return flow {

            val response = apiService.getTextToImageAsStream(
                apiKey = Constants.API_KEY,
                prompt = prompt,
                guidance = "7",
                steps = 30,
                sampler = "euler_a",
                upscale = 1,
                negativePrompt = "ugly, tiling, poorly drawn hands, poorly drawn feet, poorly drawn face, out of frame, extra limbs, disfigured, deformed, body out of frame, blurry, bad anatomy, blurred, watermark, grainy, signature, cut off, draft",
                model = "epic_diffusion_1_1"
            )

            if (response.isSuccessful) {
                emit(response)
            } else {
                Log.e("Repository response error", "${response.code()}")
                emit(response)
            }
        }.catch { e ->
            Log.e("error", "$e")
        }.flowOn(Dispatchers.IO)
    }


}