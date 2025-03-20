package com.example.coffeshop.pages

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
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
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.coffeshop.R
import com.example.coffeshop.pages.viewmodel.CoffeeViewModel
import com.example.coffeshop.route.Routes
import kotlinx.coroutines.Delay
import kotlinx.coroutines.coroutineScope

@SuppressLint("UnrememberedMutableInteractionSource")
@Composable
fun OrderPage() {
    val viewModel = CoffeeViewModel()
    Column(
        modifier = Modifier
            .padding(top = 60.dp, start = 16.dp, end = 16.dp, bottom = 76.dp)
            .fillMaxSize()
            .verticalScroll(state = rememberScrollState()),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.Top)
    ) {
        DeliveryAndPickupButtons()
        DeliveryAddress()
        AddressAndNote()
        Divider(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp, bottom = 16.dp),
            color = Color(0xFFCCCCCC),
            thickness = 1.dp
        )
        OrderDetails(viewModel)
        DiscountSection()
        PaymentSummery()
        PaymentMethod()
    }
}






@SuppressLint("UnrememberedMutableInteractionSource")
@Composable
fun TopAppBarOrder(navController: NavController) {
    Row(
        modifier = Modifier
            .fillMaxWidth(0.6f)
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
            modifier = Modifier,
            text = "Order",
            fontSize = 16.sp,
            fontFamily = FontFamily(Font(R.font.sora_semibold)),
            color = Color.Black,
        )

    }
}

@Composable
fun DeliveryAndPickupButtons() {
    // State to track the currently selected button (0 -> Delivery, 1 -> Pickup)
    var selectedOption by remember { mutableIntStateOf(0) } // 0 for Delivery, 1 for Pickup

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(),
        horizontalArrangement = Arrangement.SpaceEvenly // Space evenly between buttons
    ) {
        // Delivery Button
        val deliveryButtonColor by animateColorAsState(
            targetValue = if (selectedOption == 0) Color(0xFFC67C4E) else Color.White, // Active: 0xFFC67C4E, Inactive: White
            animationSpec = tween(
                durationMillis = 600,
                easing = androidx.compose.animation.core.FastOutSlowInEasing
            ) // Smoother animation
        )
        val deliveryTextColor by animateColorAsState(
            targetValue = if (selectedOption == 0) Color.White else Color.Black, // Text: White for active, Black for inactive
            animationSpec = tween(
                durationMillis = 600,
                easing = androidx.compose.animation.core.FastOutSlowInEasing
            ) // Smoother animation
        )
        Button(
            onClick = { selectedOption = 0 }, // Set Delivery as selected
            colors = ButtonDefaults.buttonColors(containerColor = deliveryButtonColor),
            shape = RoundedCornerShape(topStart = 8.dp, bottomStart = 8.dp),
            elevation = ButtonDefaults.buttonElevation(4.dp),
            modifier = Modifier.weight(1f) // Equal space for both buttons
        ) {
            Text(
                text = "Delivery",
                color = deliveryTextColor,
                fontSize = 16.sp,
                fontFamily = FontFamily(Font(R.font.sora_semibold))
            )
        }
        // Pickup Button
        val pickupButtonColor by animateColorAsState(
            targetValue = if (selectedOption == 1) Color(0xFFC67C4E) else Color.White, // Active: 0xFFC67C4E, Inactive: White
            animationSpec = tween(
                durationMillis = 600,
                easing = androidx.compose.animation.core.FastOutSlowInEasing
            ) // Smoother animation
        )
        val pickupTextColor by animateColorAsState(
            targetValue = if (selectedOption == 1) Color.White else Color.Black, // Text: White for active, Black for inactive
            animationSpec = tween(
                durationMillis = 600,
                easing = androidx.compose.animation.core.FastOutSlowInEasing
            ) // Smoother animation
        )
        Button(
            onClick = { selectedOption = 1 }, // Set Pickup as selected
            colors = ButtonDefaults.buttonColors(containerColor = pickupButtonColor),
            shape = RoundedCornerShape(topEnd = 8.dp, bottomEnd = 8.dp),
            elevation = ButtonDefaults.buttonElevation(4.dp),
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = "Pickup",
                color = pickupTextColor,
                fontSize = 16.sp,
                fontFamily = FontFamily(Font(R.font.sora_semibold))
            )
        }
    }
}

@Composable
fun DeliveryAddress() {
    Column(
        modifier = Modifier.padding(top = 16.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start,
    ) {
        Text(
            text = "Delivery Address",
            style = TextStyle(
                fontSize = 16.sp,
                fontFamily = FontFamily(Font(R.font.sora_semibold)),
                color = Color(0xFF242424),
            )
        )
        Text(
            modifier = Modifier.padding(top = 16.dp),
            text = "Jl. Kpg Sutoyo",
            style = TextStyle(
                fontSize = 14.sp,
                fontFamily = FontFamily(Font(R.font.sora_semibold)),
                color = Color(0xFF313131),
            )
        )
        Text(
            modifier = Modifier.padding(top = 4.dp),
            text = "Kpg. Sutoyo No. 620, Bilzen, Tanjungbalai.",
            style = TextStyle(
                fontSize = 12.sp,
                fontFamily = FontFamily(Font(R.font.sora_regular)),
                color = Color(0xFFA2A2A2),
            )
        )
    }
}

@Composable
fun AddressAndNoteButton(icon: Int, text: String) {
    val context = LocalContext.current
    Button(
        modifier = Modifier
            .wrapContentSize()
            .height(30.dp),
        onClick = {
            Toast.makeText(context, "Button Clicked", Toast.LENGTH_SHORT).show()
        },
        shape = RoundedCornerShape(16.dp),
        colors = ButtonDefaults.buttonColors(Color.White),
        elevation = ButtonDefaults.buttonElevation(4.dp),
        border = ButtonDefaults.outlinedButtonBorder
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(4.dp, Alignment.CenterHorizontally),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Icon(
                modifier = Modifier.padding(end = 4.dp),
                painter = painterResource(icon),
                contentDescription = null,
                tint = Color.Black
            )
            Text(
                text = text,
                style = TextStyle(
                    fontSize = 12.sp,
                    fontFamily = FontFamily(Font(R.font.sora_semibold)),
                    color = Color(0xFF313131),
                )
            )
        }
    }
}

@Composable
fun AddressAndNote() {
    Row(
        modifier = Modifier.padding(top = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.Start),
        verticalAlignment = Alignment.Top,
    ) {
        AddressAndNoteButton(R.drawable.ic_edit,"Edit Address")
        AddressAndNoteButton(R.drawable.ic_note,"Add Note")
    }
}

@SuppressLint("UnrememberedMutableInteractionSource")
@Composable
fun OrderDetails(viewModel: CoffeeViewModel) {
    val count by viewModel.count.observeAsState(1)

    Card(
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp), // Add elevation here
        shape = RoundedCornerShape(12.dp), // Rounded corners
        modifier = Modifier
            .fillMaxWidth(),
        colors = CardDefaults.cardColors(Color.White)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp), // Optional padding inside the Row
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Card(
                modifier = Modifier
                    .width(54.dp)
                    .height(54.dp),
                shape = RoundedCornerShape(8.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.caffee_mocha),
                    contentDescription = "image description",
                    contentScale = ContentScale.Crop
                )
            }
            Column(
                modifier = Modifier.padding(start = 16.dp),
                verticalArrangement = Arrangement.spacedBy(4.dp, Alignment.Top),
                horizontalAlignment = Alignment.Start,
            ) {
                Text(
                    text = "Caffe Mocha",
                    style = TextStyle(
                        fontSize = 16.sp,
                        fontFamily = FontFamily(Font(R.font.sora_semibold)),
                        color = Color(0xFF242424),
                    )
                )
                Text(
                    text = "Deep Foam",
                    style = TextStyle(
                        fontSize = 12.sp,
                        fontFamily = FontFamily(Font(R.font.sora_regular)),
                        color = Color(0xFFA2A2A2),
                    )
                )

            }
            Row(
                modifier = Modifier.padding(start = 64.dp),
                horizontalArrangement = Arrangement.spacedBy(18.dp, Alignment.CenterHorizontally),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Icon(
                    modifier = Modifier.clickable(
                        enabled = true, interactionSource = MutableInteractionSource(), indication = null
                    ){
                        if (viewModel.count.value != 1) {
                            viewModel.decrement()
                        }
                    },
                    painter = painterResource(R.drawable.ic_minus),
                    contentDescription = null,
                )
                Text(
                    text = "$count",
                    style = TextStyle(
                        fontSize = 14.sp,
                        fontFamily = FontFamily(Font(R.font.sora_semibold)),
                        color = Color(0xFF2A2A2A),
                    )
                )
                Icon(
                    modifier = Modifier.clickable(
                        enabled = true, interactionSource = MutableInteractionSource(), indication = null
                    ){
                        viewModel.increment()
                    },
                    imageVector = Icons.Default.Add,
                    contentDescription = null
                )
            }
        }
    }
}

@Composable
fun DiscountSection() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp, Alignment.Start),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_discount),
                contentDescription = "image description",
                contentScale = ContentScale.None
            )
            Text(
                text = "1 Discount is Applies",
                style = TextStyle(
                    fontSize = 14.sp,
                    fontFamily = FontFamily(Font(R.font.sora_semibold)),
                    color = Color(0xFF313131),
                )
            )
        }
        Image(
            imageVector = Icons.Default.KeyboardArrowRight,
            contentDescription = "image description",
            contentScale = ContentScale.None
        )
    }
}


@Composable
fun PaymentSummery() {

    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.Top),
        horizontalAlignment = Alignment.Start,
    ) {
        Text(
            text = "Payment Summary",
            style = TextStyle(
                fontSize = 16.sp,
                fontFamily = FontFamily(Font(R.font.sora_semibold)),
                color = Color(0xFF242424),
            )
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = "Price",
                style = TextStyle(
                    fontSize = 14.sp,
                    fontFamily = FontFamily(Font(R.font.sora_semibold)),
                    color = Color(0xFF313131),
                )
            )
            Text(
                text = "$ 4.53",
                style = TextStyle(
                    fontSize = 14.sp,
                    fontFamily = FontFamily(Font(R.font.sora_semibold)),
                    color = Color(0xFF242424),
                )
            )
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = "Delivery Fee",
                style = TextStyle(
                    fontSize = 14.sp,
                    fontFamily = FontFamily(Font(R.font.sora_semibold)),
                    color = Color(0xFF313131),
                )
            )
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterHorizontally),
                verticalAlignment = Alignment.CenterVertically,
            ){
                Text(
                    text = "$ 2.0",
                    style = TextStyle(
                        fontSize = 14.sp,
                        fontFamily = FontFamily(Font(R.font.sora_regular)),
                        color = Color(0xFF2A2A2A),
                        textAlign = TextAlign.Right,
                        textDecoration = TextDecoration.LineThrough,
                    )
                )
                Text(
                    text = "$ 1.0",
                    style = TextStyle(
                        fontSize = 14.sp,
                        fontFamily = FontFamily(Font(R.font.sora_semibold)),
                        color = Color(0xFF242424),
                        textAlign = TextAlign.Right,
                    )
                )
            }
        }
    }

}

@Composable
fun PaymentMethod() {
    Card(
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp), // Add elevation here
        shape = RoundedCornerShape(12.dp), // Optional rounded corners
        modifier = Modifier
            .fillMaxWidth(),
        colors = CardDefaults.cardColors(Color.White)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp), // Padding inside the Card
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(16.dp, Alignment.Start),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_wallet),
                    contentDescription = "image description",
                    contentScale = ContentScale.None
                )
                Column(
                    verticalArrangement = Arrangement.spacedBy(4.dp, Alignment.Top),
                    horizontalAlignment = Alignment.Start,
                ) {
                    Text(
                        text = "Cash/Wallet",
                        style = TextStyle(
                            fontSize = 14.sp,
                            fontFamily = FontFamily(Font(R.font.sora_semibold)),
                            color = Color(0xFF242424),
                        )
                    )
                    Text(
                        text = "$ 5.53",
                        style = TextStyle(
                            fontSize = 12.sp,
                            fontFamily = FontFamily(Font(R.font.sora_semibold)),
                            color = Color(0xFFC67C4E),
                        )
                    )
                }
            }
            Icon(
                imageVector = Icons.Default.KeyboardArrowDown,
                contentDescription = null
            )
        }
    }
}

@Composable
fun OrderScreen(navController: NavController) {
    // State to control the visibility of the Lottie animation
    var showAnimation by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .fillMaxSize(), // Optional padding
        contentAlignment = Alignment.BottomCenter // Align content to the center of the screen
    ) {
        // Show Lottie animation if triggered
        if (showAnimation) {
            LottieAnimationExample(navController)
        } else {
            // Order Button positioned at the center initially
            OrderButton(onClick = {
                showAnimation = true // Trigger the animation
            })
        }
    }
}

@Composable
fun OrderButton(onClick: () -> Unit) {
    Button(
        modifier = Modifier
            .fillMaxWidth()
            .height(76.dp)
            .padding(start = 20.dp, top = 16.dp, end = 20.dp, bottom = 16.dp),
        colors = ButtonDefaults.buttonColors(Color(0xFFC67C4E)),
        shape = RoundedCornerShape(16.dp),
        onClick = onClick // Pass the click event to the parent
    ) {
        Text(
            text = "Order",
            style = TextStyle(
                fontSize = 16.sp,
                fontFamily = FontFamily(Font(R.font.sora_semibold)),
                color = Color(0xFFFFFFFF),
            )
        )
    }
}

@Composable
fun LottieAnimationExample(navController: NavController) {
    // Load the Lottie composition from a JSON file in the raw resources folder
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.animated_success))

    // State for animation visibility
    var isAnimationVisible by remember { mutableStateOf(true) }

    // Lottie animation progress state
    val progress by animateLottieCompositionAsState(
        composition = composition,
        iterations = 1 // Play animation only once
    )

    // Check if the animation has finished
    if (progress >= 1.0f) {
        isAnimationVisible = false // Hide animation when it finishes
        navController.navigate(Routes.homeScreen)
    }

    // Show the animation if it's still visible
    if (isAnimationVisible) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            contentAlignment = Alignment.Center
        ) {
            LottieAnimation(
                composition = composition,
                progress = { progress }, // Attach the progress to the animation
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp) // Customize size as needed
            )
        }
    }
}





@Preview(showBackground = true)
@Composable
fun OrderPagePreview() {
    val viewModel = CoffeeViewModel()
    OrderDetails(viewModel)
}

@Preview(showBackground = true)
@Composable
fun OrderPagePreview1() {
    OrderPage()
}