package com.example.coffeshop.pages



import android.annotation.SuppressLint
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.coffeshop.R
import com.example.coffeshop.route.Routes


@Composable
fun DetailItemScreen(
    navController: NavController,
    drawable: Int,
    text1: String,
    text2: String,
    price: String
) {

    val bigImage = bigImages.find { it.drawable == drawable }?.bigImage ?: drawable

    DetailItem(
        navController = navController,
        image = bigImage,
        text = text1,
        coffeePrice = price
    )
}

private data class DrawableBigImagePair(
    @DrawableRes val drawable: Int,
    @DrawableRes val bigImage: Int
)


private val bigImages = listOf(
    R.drawable.caffee_mocha to R.drawable.big_caffee_mocha
).map { DrawableBigImagePair(it.first,it.second) }

@SuppressLint("UnrememberedMutableInteractionSource")
@Composable
fun DetailItem(
    navController: NavController,
    image: Int,
    text: String,
    coffeePrice: String
) {

    Column(
        modifier = Modifier.fillMaxSize()
            .padding(top = 50.dp, bottom = 32.dp)
            .verticalScroll(
                state = rememberScrollState(),
            )
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .aspectRatio(327f / 202f) ,
        ) {
            Image(
                modifier = Modifier
                    .fillMaxWidth(),
                painter = painterResource(image),
                contentDescription = "image description",
                contentScale = ContentScale.Crop,
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.spacedBy(42.dp, Alignment.Start),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Column(
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.Start,
            ) {
                Text(
                    text = text,
                    style = TextStyle(
                        fontSize = 20.sp,
                        fontFamily = FontFamily(Font(R.font.sora_semibold)),
                        color = Color(0xFF242424),
                    )
                )
                Text(
                    text = "Ice/Hot",
                    style = TextStyle(
                        fontSize = 12.sp,
                        fontFamily = FontFamily(Font(R.font.sora_regular)),
                        color = Color(0xFFA2A2A2),
                    )
                )
                Row(
                    modifier = Modifier.padding(top = 16.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.Start),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_star),
                        contentDescription = "image description",
                        contentScale = ContentScale.None
                    )
                    Text(
                        text = buildAnnotatedString {
                            withStyle(
                                style = SpanStyle(
                                    fontSize = 16.sp,
                                    fontFamily = FontFamily(Font(R.font.sora_semibold)),
                                    color = Color(0xFF2A2A2A)
                                )
                            ) {
                                append("4.8 ")
                            }
                            withStyle(
                                style = SpanStyle(
                                    fontSize = 16.sp,
                                    fontFamily = FontFamily(Font(R.font.sora_regular)),
                                    color = Color(0xFFA2A2A2)
                                )
                            ) {
                                append("(230)")
                            }
                        }
                    )

                }
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_delivery_truck),
                    tint = Color(0xFFc67c4e),
                    contentDescription = "Custom Icon"
                )
                Icon(
                    painter = painterResource(id = R.drawable.ic_coffee_bean),
                    tint = Color(0xFFc67c4e),
                    contentDescription = "Custom Icon"
                )
                Icon(
                    painter = painterResource(id = R.drawable.ic_packaging),
                    tint = Color(0xFFc67c4e),
                    contentDescription = "Custom Icon"
                )
            }
        }
        Divider(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            color = Color(0xFFCCCCCC),
            thickness = 1.dp
        )
        Text(
            modifier = Modifier
                .padding(16.dp),
            text = "Description",
            style = TextStyle(
                fontSize = 16.sp,
                fontFamily = FontFamily(Font(R.font.sora_semibold)),
                color = Color(0xFF242424),
            )
        )
        ExpandableText()
        Text(
            modifier = Modifier.padding(16.dp),
            text = "Size",
            style = TextStyle(
                fontSize = 16.sp,
                fontFamily = FontFamily(Font(R.font.sora_semibold)),
                color = Color(0xFF242424),
            )
        )
        Row(
            modifier = Modifier
                .padding(start = 16.dp, end = 16.dp, top = 8.dp, bottom = 8.dp)
                .fillMaxWidth(),
        ) {
            SelectableTextOnly()
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(4.dp, Alignment.Top),
                horizontalAlignment = Alignment.Start,
            ) {
                Text(
                    text = "Price",
                    style = TextStyle(
                        fontSize = 14.sp,
                        fontFamily = FontFamily(Font(R.font.sora_regular)),
                        color = Color(0xFF909090),
                    )
                )
                Text(
                    text = "$coffeePrice",
                    style = TextStyle(
                        fontSize = 18.sp,
                        lineHeight = 27.sp,
                        fontFamily = FontFamily(Font(R.font.sora_semibold)),
                        color = Color(0xFFC67C4E),
                    )
                )
            }
            Button(
                modifier = Modifier
                    .width(217.dp)
                    .padding(start = 20.dp, top = 16.dp, end = 20.dp, bottom = 16.dp),
                onClick = {
                    navController.navigate(Routes.orderPage)
                },
                shape = RoundedCornerShape(16.dp),
                colors = ButtonDefaults.buttonColors(Color(0xFFC67C4E))
            ) {
                Text(
                    text = "Buy Now",
                    style = TextStyle(
                        fontSize = 16.sp,
                        fontFamily = FontFamily(Font(R.font.sora_semibold)),
                        color = Color(0xFFFFFFFF),
                    )
                )
            }
        }

    }

}


@SuppressLint("UnrememberedMutableInteractionSource")
@Composable
fun TopAppBar(navController: NavController) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Icon(
            modifier = Modifier
                .size(32.dp)
                .clickable(
                    interactionSource = MutableInteractionSource(),
                    indication = null
                ) {
                    navController.popBackStack()
                },
            imageVector = Icons.Default.KeyboardArrowLeft,
            contentDescription = null
        )
        Text(
            text = "Detail",
            fontSize = 16.sp,
            fontFamily = FontFamily(Font(R.font.sora_semibold)),
            color = Color.Black,
        )
        Icon(
            painter = painterResource(R.drawable.ic_heart),
            contentDescription = null,
            tint = Color.Black
        )
    }
}

@Composable
fun SelectableTextOnly() {
    // State to track the currently selected text
    var selected by remember { mutableStateOf("S") }

    // Options for selection
    val options = listOf("S", "M", "L")

    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        options.forEach { option ->
            Card(
                modifier = Modifier
                    .width(96.dp)
                    .height(41.dp)
                    .clickable(
                        interactionSource = MutableInteractionSource(),
                        indication = null
                    ) { selected = option }, // Updates selected text on click
                shape = RoundedCornerShape(12.dp), // Rounded corners
                elevation = CardDefaults.cardElevation(4.dp),
                colors = CardDefaults.cardColors(Color.White)// Elevation to give a shadow effect
            ) {
                Box(
                    modifier = Modifier
                        .border(
                            width = 1.dp,
                            color = if (selected == option) Color(0xFFc67c4e) else Color.Transparent,
                            shape = RoundedCornerShape(12.dp)
                        )
                        .fillMaxSize(), // Ensures the Box fills the Card
                    contentAlignment = Alignment.Center // Centers the text within the box
                ) {
                    Text(
                        text = option,
                        textAlign = TextAlign.Center, // Centers text alignment
                        style = TextStyle(
                            fontSize = 16.sp,
                            fontFamily = FontFamily(Font(R.font.sora_semibold)),
                            color = Color.Black // Default text color
                        )
                    )
                }
            }

        }
    }
}
@SuppressLint("UnrememberedMutableInteractionSource")
@Composable
fun ExpandableText() {
    // State to track if the text is expanded
    var isExpanded by remember { mutableStateOf(false) }

    // Text for the preview and full content
    val fullText = stringResource(R.string.extendedDescription)
    val previewText = stringResource(R.string.description)

    val displayText = if (isExpanded) fullText else previewText

    Text(
        text = buildAnnotatedString {
            append(displayText)
            withStyle(
                style = SpanStyle(
                    color = Color(0xFFc67c4e), // Color for "Read More" or "Read Less"
                )
            ) {
                append(if (isExpanded) " Read Less" else " Read More")
            }
        },
        modifier = Modifier
            .padding(start = 16.dp, top = 8.dp, end = 16.dp)
            .clickable(
                interactionSource = MutableInteractionSource(),
                indication = null
            ) { isExpanded = !isExpanded }, // Toggle expanded state
        fontSize = 14.sp,
        lineHeight = 21.sp,
        color = Color(0xFFA2A2A2)
    )
}

@Preview(showBackground = true)
@Composable
fun DetailItemPreview(){
    DetailItem(
        navController = rememberNavController(),
        image = R.drawable.big_caffee_mocha,
        text = R.string.Caffee_Mocha.toString(),
        coffeePrice = "$ 4.53"
    )
}