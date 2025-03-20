package com.example.coffeshop.pages


import android.annotation.SuppressLint
import androidx.annotation.DrawableRes
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.coffeshop.R
import com.example.coffeshop.pages.categoryscreen.AllCoffeeScreen
import com.example.coffeshop.pages.categoryscreen.AmericanoScreen
import com.example.coffeshop.pages.categoryscreen.LatteScreen
import com.example.coffeshop.pages.categoryscreen.MachiatoScreen


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomePage(navController: NavController) {

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        LocationAndSearch()
        CoffeeCategory(navController)
    }

}

@Composable
fun LocationAndSearch() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(290.dp)
    ) {
        Column(
            modifier = Modifier
                .background(
                    brush = Brush.horizontalGradient(
                        colors = listOf(
                            Color(0xff2b2b2a), Color(0xff161616)
                        )
                    )
                )
                .height(200.dp)
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            Text(
                text = "Location",
                color = Color.Gray,
                textAlign = TextAlign.Center,
                fontFamily = FontFamily(Font(R.font.sora_regular)),
                fontSize = 12.sp
            )
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Bilzen, Tanjungbalai",
                    color = Color.White,
                    textAlign = TextAlign.Center,
                    fontFamily = FontFamily(Font(R.font.sora_semibold)),
                    fontSize = 14.sp
                )
                Icon(
                    imageVector = Icons.Default.KeyboardArrowDown,
                    contentDescription = null,
                    tint = Color.White
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                SearchBar()
                IconsCard(
                    icon = R.drawable.settings,
                    cardSize = 52.dp,
                    corner = 12.dp,
                    iconSize = 24.dp
                )
            }


        }
        Card(
            modifier = Modifier
                .height(140.dp)
                .width(327.dp)
                .align(Alignment.BottomCenter),
            shape = RoundedCornerShape(24.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 12.dp)
        ) {
            Image(
                painter = painterResource(R.drawable.promo_add),
                contentDescription = "Promo Add",
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
        }


    }
}


@Composable
fun SearchBar() {
    var searchText by remember { mutableStateOf(TextFieldValue("")) }

    TextField(
        value = searchText,
        onValueChange = { searchText = it },
        placeholder = { Text("Search coffee", fontSize = 16.sp, color = Color.Gray) },
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = "Search Icon",
                tint = Color.White
            )
        },
        colors = TextFieldDefaults.colors(
            unfocusedContainerColor = Color.DarkGray, // Background color when not focused
            focusedContainerColor = Color.DarkGray, // Background color when focused
            cursorColor = Color.White, // Cursor color
            focusedIndicatorColor = Color.Transparent, // Hide underline
            unfocusedIndicatorColor = Color.Transparent // Hide underline
        ),
        modifier = Modifier
            .height(52.dp)
            .width(250.dp)
            .clip(RoundedCornerShape(12.dp))
    )

}


@Composable
fun IconsCard(@DrawableRes icon: Int, cardSize: Dp, corner: Dp, iconSize: Dp) {
    Card(
        modifier = Modifier
            .size(cardSize),
        shape = RoundedCornerShape(corner),
        colors = CardDefaults.cardColors((Color(0xffc67c4e)))
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Icon(
                painter = painterResource(icon),
                contentDescription = "Search",
                tint = Color.White,
                modifier = Modifier.size(iconSize)
            )
        }


    }
}

@Composable
fun CoffeeCategoryButton(name: String, isSelected: Boolean, onClick: () -> Unit) {
    // Animate the background color
    val backgroundColor by animateColorAsState(
        targetValue = if (isSelected) Color(0xFFC67C4E) else Color.LightGray, // Active/Inactive colors
        animationSpec = tween(durationMillis = 200, easing = androidx.compose.animation.core.FastOutSlowInEasing)
    )

    // Animate the text size
    val textSize by animateDpAsState(
        targetValue = if (isSelected) 20.dp else 16.dp, // Larger size when selected
        animationSpec = tween(durationMillis = 200, easing = androidx.compose.animation.core.FastOutSlowInEasing)
    )

    Button(
        onClick = onClick, // Calls parent function to update selected state
        colors = ButtonDefaults.buttonColors(
            containerColor = if (isSelected) backgroundColor else Color.White
        ),
        shape = RoundedCornerShape(6.dp),
        elevation = ButtonDefaults.buttonElevation(defaultElevation = 8.dp)
    ) {
        Text(
            text = name,
            fontFamily = FontFamily(Font(R.font.sora_semibold)),
            fontSize = textSize.toSp(),
            color = if (isSelected) Color.White else Color.Black
        )
    }
}

// Extension function to convert Dp to Sp
@Composable
fun Dp.toSp(): TextUnit {
    return this.value.sp
}


@Composable
fun CoffeeCategory(navController: NavController) {
    var selectedCategory by remember { mutableStateOf("All Coffee") } // Track selected category

    val categories = listOf("All Coffee", "Machiato", "Latte", "Americano")

    LazyRow(
        modifier = Modifier.padding(top = 4.dp, start = 16.dp, end = 16.dp, bottom = 4.dp)
    ) {
        items(categories.size) { index ->
            val category = categories[index]
            CoffeeCategoryButton(
                name = category,
                isSelected = selectedCategory == category,
                onClick = {
                    selectedCategory = category
                } // Updates selected button
            )
            Spacer(modifier = Modifier.width(16.dp))
        }
    }
    GetAllCategory(selectedCategory, navController)
}




@Composable
fun GetAllCategory(string: String,navController: NavController) {
    when (string) {
        "All Coffee" -> {
            AllCoffeeScreen(navController)
        }

        "Machiato" -> {
            MachiatoScreen()
        }

        "Latte" -> {
            LatteScreen()
        }

        "Americano" -> {
            AmericanoScreen()
        }
    }
}



