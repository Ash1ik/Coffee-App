package com.example.coffeshop.pages.categoryscreen

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.sp
import com.example.coffeshop.R

@Composable
fun MachiatoScreen() {
    Text(
        text = "Welcome to Machiato",
        fontFamily = FontFamily(Font(R.font.sora_semibold)),
        fontSize = 24.sp,
        color = Color.Black
    )
}