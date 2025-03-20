package com.example.coffeshop.pages

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.coffeshop.R
import com.example.coffeshop.route.Routes

@Composable
fun Onboarding(navController: NavController) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

            Image(
                modifier = Modifier.fillMaxSize().weight(1.5f),
                painter = painterResource(id = R.drawable.im_coffee_onboarding),
                contentScale = ContentScale.Crop,
                contentDescription = null,
            )
        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .background(color = Color.Black),
            contentAlignment = Alignment.TopCenter
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(16.dp)
            ) {
                Text(
                    text = "Fall in Love with\nCoffee in Blissful\nDelight!",
                    color = Color.White,
                    fontSize = 32.sp,
                    lineHeight = 50.sp,
                    textAlign = TextAlign.Center,
                    fontFamily = FontFamily(Font(R.font.sora_semibold)),
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Welcome to our cozy coffee corner, where\nevery cup is a delightful for you.",
                    color = Color.Gray,
                    textAlign = TextAlign.Center,
                    fontFamily = FontFamily(Font(R.font.sora_regular)),
                    fontSize = 14.sp
                )
                Spacer(modifier = Modifier.height(16.dp))

                // Get Started Button
                Button(
                    onClick = {
                        navController.navigate(Routes.homeScreen)
                    },
                    shape = RoundedCornerShape(16.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xffc67c4e)),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp)
                        .padding(horizontal = 18.dp)
                ) {
                    Text(
                        text = "Get Started",
                        color = Color.White,
                        fontSize = 16.sp,
                        fontFamily = FontFamily(Font(R.font.sora_semibold)))
                }
            }
        }

    }

}
