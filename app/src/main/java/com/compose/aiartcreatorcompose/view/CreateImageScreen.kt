package com.compose.aiartcreatorcompose.view

import android.annotation.SuppressLint
import android.graphics.Bitmap
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.compose.aiartcreatorcompose.R
import com.compose.aiartcreatorcompose.viewmodel.ImageViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import com.compose.aiartcreatorcompose.model.Result


@SuppressLint("CoroutineCreationDuringComposition", "RememberReturnType", "SuspiciousIndentation")
@Composable
fun CreateImageScreen(navController: NavController, prompt: String?, category: String?) {


    Column(modifier = Modifier.fillMaxSize()) {

        Image(painter = painterResource(id = R.drawable.btn_back),
            contentDescription ="",
            modifier = Modifier
                .width(70.dp)
                .height(70.dp)
                .padding(start = 20.dp))


        Image(painter = painterResource(id = R.drawable.img_home25) , contentDescription = "",
            modifier = Modifier
                .fillMaxWidth()
                .align(CenterHorizontally))


        Text(text = "$prompt + $category",
            modifier = Modifier.padding(start = 20.dp, top = 10.dp),
            style = LocalTextStyle.current.copy(
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.SansSerif,
                fontSize = 16.sp,
                color = Color.Black
            ))
        
        
        Spacer(modifier = Modifier.padding(10.dp))

        Card(shape = RoundedCornerShape(10.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 10.dp),
            modifier = Modifier.fillMaxSize()) {

            Spacer(modifier = Modifier.padding(10.dp))
            Row(Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly,) {

                Image(painter = painterResource(id = R.drawable.btn_save), contentDescription =""
                    , modifier = Modifier.
                clickable {
                    println("tıklandı")
                })
                Image(painter = painterResource(id = R.drawable.btn_like), contentDescription ="" )
                Image(painter = painterResource(id = R.drawable.btn_transform), contentDescription ="" )

            }
        }

    }
}

@Preview
@Composable
fun PreviewCreateImageScreen() {

    val navController = rememberNavController()
    CreateImageScreen(navController = navController, prompt = "", category = "")
}






