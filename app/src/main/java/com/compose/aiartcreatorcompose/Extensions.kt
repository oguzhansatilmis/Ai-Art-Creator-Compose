package com.compose.aiartcreatorcompose

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import okhttp3.ResponseBody
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.lang.Exception

fun ResponseBody.convertResponseBodyToBitmap(responseBody: ResponseBody): Bitmap? {

    return try {
        val byteArray = responseBody.bytes()
        val inputStream = ByteArrayInputStream(byteArray)
        BitmapFactory.decodeStream(inputStream)
    } catch (e: Exception) {
        e.printStackTrace()
        null
    }
}

fun Bitmap.convertBitmapToByteArray(bitmap: Bitmap): ByteArray? {

    return try {
        val stream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.PNG, 90, stream)
        return stream.toByteArray()
    } catch (e: Exception) {
        e.printStackTrace()
        null
    }
}