package com.example.coffeshop

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.with
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.coffeshop.pages.DetailItemScreen
import com.example.coffeshop.pages.HomePage
import com.example.coffeshop.pages.Onboarding
import com.example.coffeshop.pages.OrderButton
import com.example.coffeshop.pages.OrderPage
import com.example.coffeshop.pages.OrderScreen
import com.example.coffeshop.pages.TopAppBar
import com.example.coffeshop.pages.TopAppBarOrder
import com.example.coffeshop.pages.bottomnav.BottomNavBar
import com.example.coffeshop.route.Routes

class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            NavHost(navController = navController,startDestination = Routes.onBoardScreen, builder = {
                composable(Routes.onBoardScreen){
                    Onboarding(navController)
                }
                composable(Routes.homeScreen){
                    Scaffold(
                        bottomBar = { BottomNavBar() },
                    ){
                        HomePage(navController)
                    }
                }
                composable(
                    Routes.detailItemScreen+"/{drawable}/{text}/{text2}/{price}",
                    arguments = listOf(
                        navArgument("drawable") { type = NavType.IntType },
                        navArgument("text") { type = NavType.StringType },
                        navArgument("text2") { type = NavType.StringType },
                        navArgument("price") { type = NavType.StringType }
                    )
                ) { backStackEntry ->
                    val drawable = backStackEntry.arguments?.getInt("drawable") ?: R.drawable.promo_add
                    val text = backStackEntry.arguments?.getString("text") ?: "Unknown"
                    val text2 = backStackEntry.arguments?.getString("text2") ?: "Unknown"
                    val price = backStackEntry.arguments?.getString("price") ?: "Unknown"

                    Scaffold(
                        topBar = {
                            TopAppBar(navController)
                        },
                    ) {
                        DetailItemScreen(navController, drawable, text, text2, price)
                    }
                }
                composable(Routes.orderPage) {
                    Scaffold(
                        topBar = {
                            TopAppBarOrder(navController)
                        },
                        bottomBar = {
                            OrderScreen(navController)
                        }
                    ) {
                        OrderPage()
                    }
                }
            })
        }
    }
}





