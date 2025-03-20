package com.example.coffeshop.pages.categoryscreen


import android.annotation.SuppressLint
import android.net.Uri
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.coffeshop.R
import com.example.coffeshop.pages.IconsCard
import com.example.coffeshop.route.Routes

@Composable
fun AllCoffeeScreen(navController: NavController) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 52.dp)
    ) {
        items(allCoffeeData) { item ->
            CoffeeCard(item.drawable, item.text, item.text2, item.price, navController)
        }
    }
}


@SuppressLint("UnrememberedMutableInteractionSource")
@Composable
fun CoffeeCard(
    @DrawableRes drawable: Int,
    @StringRes text: Int,
    @StringRes text2: Int,
    @StringRes price: Int,
    navController: NavController
) {

    val encodedText = Uri.encode(stringResource(text))
    val encodedText2 = Uri.encode(stringResource(text2))
    val encodedPrice = Uri.encode(stringResource(price))


    Column(
        modifier = Modifier.padding(bottom = 16.dp)
    ) {
        Card(
            modifier = Modifier
                .width(156.dp)
                .background(color = Color.White),
            shape = RoundedCornerShape(14.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
        ) {
            Column(
                modifier = Modifier
                    .padding(8.dp),
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Box(
                        modifier = Modifier.fillMaxWidth()
                    ){
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(128.dp)
                                .width(140.dp),
                            shape = RoundedCornerShape(14.dp),
                            elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
                        ) {
                            Image(
                                modifier = Modifier.fillMaxSize()
                                    .clickable(
                                        onClick = {
                                            navController.navigate(Routes.detailItemScreen + "/$drawable/$encodedText/$encodedText2/$encodedPrice")
                                        },
                                        interactionSource = MutableInteractionSource(),
                                        indication = null
                                    ),
                                painter = painterResource(drawable),
                                contentScale = ContentScale.Crop,
                                contentDescription = null,
                            )
                        }
                        CoffeeRating(modifier = Modifier.align(Alignment.TopEnd))
                    }
                }
                Text(
                    text = stringResource(text),
                    fontFamily = FontFamily(Font(R.font.sora_semibold)),
                    fontSize = 14.sp,
                    modifier = Modifier.padding(top = 8.dp)
                )
                Text(
                    text = stringResource(text2),
                    fontFamily = FontFamily(Font(R.font.sora_regular)),
                    fontSize = 12.sp,
                    color = Color.LightGray,
                    modifier = Modifier.padding(top = 4.dp)
                )
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Text(
                        text = stringResource(price),
                        fontFamily = FontFamily(Font(R.font.sora_semibold)),
                        fontSize = 16.sp
                    )
                    IconsCard(
                        icon = R.drawable.ic_add,
                        cardSize = 26.dp,
                        corner = 6.dp,
                        iconSize = 16.dp
                    )
                }

            }
        }
    }

}



private data class DrawableStringPair(
    @DrawableRes val drawable: Int,
    @StringRes val text: Int,
    @StringRes val text2: Int,
    @StringRes val price: Int
)


private val allCoffeeData = listOf(
    Triple(
        R.drawable.caffee_mocha,
        R.string.Caffee_Mocha,
        R.string.Deep_Foam
    ) to R.string.Mocha_Price,
    Triple(
        R.drawable.flat_white,
        R.string.Flat_White,
        R.string.Espresso
    ) to R.string.Flat_White_Price,
    Triple(
        R.drawable.caffee_mocha,
        R.string.Caffee_Mocha,
        R.string.Deep_Foam
    ) to R.string.Mocha_Price,
    Triple(
        R.drawable.flat_white,
        R.string.Flat_White,
        R.string.Espresso
    ) to R.string.Flat_White_Price,
    Triple(
        R.drawable.caffee_mocha,
        R.string.Caffee_Mocha,
        R.string.Deep_Foam
    ) to R.string.Mocha_Price,
    Triple(
        R.drawable.flat_white,
        R.string.Flat_White,
        R.string.Espresso
    ) to R.string.Flat_White_Price
).map {
    DrawableStringPair(
        drawable = it.first.first,
        text = it.first.second,
        text2 = it.first.third,
        price = it.second
    )
}

@Composable
fun CoffeeRating(modifier: Modifier) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(bottomStart = 20.dp, topEnd = 12.dp),
        colors = CardDefaults.cardColors(Color.Transparent.copy(alpha = 0.5f))
    ) {
        Row(
            Modifier
                .width(51.dp)
                .height(28.dp)
                .padding(8.dp),
            horizontalArrangement = Arrangement.spacedBy(4.dp, Alignment.Start),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Icon(
                painter = painterResource(R.drawable.ic_star),
                tint = Color(0xffFBBE21),
                contentDescription = "Star"
            )
            Text(
                text = "4.8",
                style = TextStyle(
                    fontSize = 8.sp,
                    lineHeight = 12.sp,
                    fontFamily = FontFamily(Font(R.font.sora_semibold)),
                    fontWeight = FontWeight(600)
                ),
                color = Color.White
            )

        }
    }

}


@Preview(showBackground = true)
@Composable
fun CoffeeCardPreview() {
    AllCoffeeScreen(navController = NavController(LocalContext.current))

}