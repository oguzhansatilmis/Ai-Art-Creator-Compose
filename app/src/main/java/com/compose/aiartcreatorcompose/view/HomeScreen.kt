package com.compose.aiartcreatorcompose.view


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.compose.aiartcreatorcompose.R
import com.compose.aiartcreatorcompose.model.Category
import com.compose.aiartcreatorcompose.navigation.Screen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController) {

    val categoryList: ArrayList<Category> = arrayListOf()

    categoryList.add(Category(R.drawable.img_home16, "Cyberpunk"))
    categoryList.add(Category(R.drawable.img_home, "Home"))
    categoryList.add(Category(R.drawable.img_home19, "Animation"))
    categoryList.add(Category(R.drawable.img_home20, "3D Render"))
    categoryList.add(Category(R.drawable.img_home6, "Pixelart"))
    categoryList.add(Category(R.drawable.img_home8, "Pastel"))
    categoryList.add(Category(R.drawable.img_home17, "Cinematic"))

    var promt by remember { mutableStateOf(TextFieldValue("")) }
    var categoryText by remember { mutableStateOf("") }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Text(
                    text = "Explore ",
                    modifier = Modifier.padding(20.dp),
                    style = LocalTextStyle.current.copy(
                        fontFamily = FontFamily.SansSerif,
                        fontWeight = FontWeight.Bold,
                        fontSize = 25.sp
                    )
                )
                Text(
                    text = "Lorem Ipsum ",
                    modifier = Modifier.padding(20.dp),
                    style = LocalTextStyle.current.copy(
                        fontFamily = FontFamily.SansSerif,
                        fontSize = 20.sp
                    )
                )

            }
            Image(
                painter = painterResource(id = R.drawable.btn_settings),
                contentDescription = "",
                modifier = Modifier
                    .size(100.dp, 100.dp)
                    .padding(20.dp)

            )
        }
        Box(modifier = Modifier.padding(10.dp)) {
            Image(
                painter = painterResource(id = R.drawable.rectangle_938),
                contentDescription = ""
            )
            Text(
                text = "Select From Examples   >",
                modifier = Modifier
                    .align(Center),
                style = LocalTextStyle.current.copy(
                    fontSize = 11.sp,
                    fontFamily = FontFamily.SansSerif,
                    fontWeight = FontWeight.Bold,
                )
            )
        }
        OutlinedTextField(
            value = promt,
            onValueChange = { newText ->
                promt = newText
            },
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Done,
                keyboardType = KeyboardType.Text,
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
                .background(Color.Transparent),
            maxLines = 1,
            shape = RoundedCornerShape(12.dp),
            placeholder = {
                Text("Type Something...")
            }
        )
        Text(
            text = "Select an Category",
            style = LocalTextStyle.current.copy(
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.SansSerif,
                fontSize = 16.sp,
                color = Color.Black
            ),
            modifier = Modifier.padding(5.dp)
        )
       categoryText = CategoryList(categoryList = categoryList)

        Button(
            onClick = {
                val promptText = promt.text
                val categoryText = categoryText

                println("Home Screen ")
                println(promptText)
                println(categoryText)

                navController.navigate("${Screen.CreateImage.route}?prompt=$promptText&category=$categoryText")

            },

            modifier = Modifier
                .align(CenterHorizontally)
                .padding(10.dp),
            colors = ButtonDefaults.buttonColors(
                contentColor = Color.Black,
                containerColor = Color.LightGray
            )
        ) {
            Text(text = "Create Image",)
        }
    }
}

@Preview
@Composable
fun preview() {
    val navController = rememberNavController()
    HomeScreen(navController = navController)
}

@Composable
fun CategoryList(categoryList: List<Category>):String{

    var selectedIndex by remember { mutableIntStateOf(-1) }
    var  categoryText by remember { mutableStateOf("") }

    LazyColumn(
        modifier = Modifier
            .padding(5.dp)
            .height(500.dp)
    ) {
        itemsIndexed(categoryList) { index, item ->

            ClickableItem(
                item = item,
                isSelected = index == selectedIndex,

                ) {
                selectedIndex = if (it) index else index
                categoryText = categoryList[selectedIndex].categoryText
            }
        }
    }

    return categoryText
}


@Composable
fun ClickableItem(item: Category, isSelected: Boolean, onItemSelected: (Boolean) -> Unit) {

    Column(modifier = Modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
        ) {

            Spacer(modifier = Modifier.padding(7.dp))
            Image(
                painter = painterResource(id = item.imageId),
                contentDescription = "",
                modifier = Modifier.align(CenterVertically)
            )
            Spacer(modifier = Modifier.padding(start = 20.dp))
            Text(
                text = item.categoryText,
                modifier = Modifier
                    .weight(1f)
                    .align(CenterVertically)
                    .padding(10.dp),
                style = LocalTextStyle.current.copy(
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = FontFamily.SansSerif
                )
            )

            Spacer(modifier = Modifier.padding(start = 50.dp))
            Box(modifier = Modifier
                .align(CenterVertically)
                .clickable {
                    onItemSelected(!isSelected)
                }) {

                Image(

                    painter = painterResource(id = if (isSelected) R.drawable.rectangle_920 else R.drawable.rectangle_919),
                    contentDescription = "",
                    modifier = Modifier
                )
                Text(
                    text = if (isSelected) "Using" else "Use",
                    modifier = Modifier.align(Center),
                    colorResource(id = if (isSelected) R.color.white else R.color.black),
                    style = LocalTextStyle.current.copy(
                        fontFamily = FontFamily.SansSerif,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                    )
                )
            }
        }
    }


}







