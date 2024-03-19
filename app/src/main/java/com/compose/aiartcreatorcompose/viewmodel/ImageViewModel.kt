package com.compose.aiartcreatorcompose.viewmodel

import android.graphics.Bitmap
import android.view.View
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.compose.aiartcreatorcompose.convertResponseBodyToBitmap
import com.compose.aiartcreatorcompose.repository.ImageRepositoryImpl
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import okhttp3.ResponseBody
import retrofit2.Response
import java.lang.Exception
import javax.inject.Inject
import com.compose.aiartcreatorcompose.model.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest

@HiltViewModel
class ImageViewModel @Inject constructor(
    private val imageRepositoryImpl: ImageRepositoryImpl
) : ViewModel() {

    private val _imageResponseState = MutableStateFlow<Result<Response<ResponseBody>>>(Result.Loading.create())
    val imageResponseState: StateFlow<Result<Response<ResponseBody>>> = _imageResponseState

    fun getData(prompt: String) {
        viewModelScope.launch {
            fetchData(prompt)

            val imageResponse = imageResponseState
            imageResponse.collectLatest {
                when (it) {
                    is Result.Success -> {
                        val data = it.data
                        val responseBody = data.body()
                        responseBody?.let {
                            val bitmap = responseBody.convertResponseBodyToBitmap(responseBody = responseBody)
                        //  callback(bitmap) // CHAT GPT

                        }
                    }
                    is Result.Loading -> {
                       // callback(null) // or handle loading state accordingly
                    }
                    is Result.Error -> {
                        val message = it.message
                        println("error $message")
                      //  callback(null) // or handle error state accordingly
                    }
                }
            }
        }
    }

    private suspend fun fetchData(prompt:String) {

        viewModelScope.launch {
            try {
                imageRepositoryImpl.createImage(prompt = prompt).collect{response->
                    if (response.isSuccessful){
                        _imageResponseState.value =Result.Success(response)
                    }else{
                        _imageResponseState.value = Result.Error("image not created")
                    }

                }
            }catch (e: Exception){
                _imageResponseState.value = Result.Error("image not created $e")
            }
        }

    }

}