package com.example.coffeshop.pages.bottomnav


import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.coffeshop.R


data class NavItem(
    val icon: Int,
)
@Composable
fun BottomNavBar() {
    var selectedIndex by remember { mutableIntStateOf(0) }

    val navItemList = listOf(
        NavItem(icon = R.drawable.ic_home),
        NavItem(icon = R.drawable.ic_heart),
        NavItem(icon = R.drawable.ic_bag),
        NavItem(icon = R.drawable.ic_notification)
    )

    // Calculate the width of each navigation item based on available screen width
    val screenWidth = with(LocalDensity.current) {
        LocalConfiguration.current.screenWidthDp.dp
    }
    val itemWidth = screenWidth / navItemList.size // Equal width for each item

    // Animate the horizontal position of the indicator
    val indicatorOffset by animateDpAsState(
        targetValue = ((selectedIndex * itemWidth.value) + (itemWidth.value / 2) - 6).dp, // Center the indicator under the selected item
        animationSpec = tween(durationMillis = 300) // Smooth transition
    )

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
            .clip(RoundedCornerShape(24.dp))
            .background(Color.White) // Background for the navigation bar
    ) {
        Row(
            modifier = Modifier.fillMaxSize(),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            navItemList.forEachIndexed { index, navItem ->
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .weight(1f)
                        .clickable(
                            interactionSource = MutableInteractionSource(),
                            indication = null
                        ) { selectedIndex = index }
                ) {
                    Icon(
                        painter = painterResource(navItem.icon),
                        contentDescription = null,
                        tint = if (selectedIndex == index) Color(0xFFC67C4E) else Color.Gray,
                        modifier = Modifier.size(24.dp)
                    )
                }
            }
        }

        // Indicator positioned dynamically below the selected item
        Box(
            modifier = Modifier
                .padding(bottom = 8.dp)
                .offset(x = indicatorOffset) // Dynamic horizontal position
                .align(Alignment.BottomStart) // Align to the bottom
                .size(width = 12.dp, height = 4.dp) // Fixed indicator size
                .background(color = Color(0xFFC67C4E), shape = RoundedCornerShape(50)) // Rounded shape for the indicator
        )
    }
}
