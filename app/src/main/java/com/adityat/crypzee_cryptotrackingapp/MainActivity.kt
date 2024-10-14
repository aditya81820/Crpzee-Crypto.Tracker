package com.adityat.crypzee_cryptotrackingapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.adityat.crypzee_cryptotrackingapp.MainUI.Dashboard
import com.adityat.crypzee_cryptotrackingapp.MainUI.Explore
import com.adityat.crypzee_cryptotrackingapp.MainUI.Gainer
import com.adityat.crypzee_cryptotrackingapp.MainUI.Losers
import com.adityat.crypzee_cryptotrackingapp.MainUI.MarketCap
import com.adityat.crypzee_cryptotrackingapp.MainUI.Settings
import com.adityat.crypzee_cryptotrackingapp.MainUI.WishList
import com.adityat.crypzee_cryptotrackingapp.SignUporsigninScreens.SignInPage
import com.adityat.crypzee_cryptotrackingapp.SignUporsigninScreens.SignUphomepage
import com.adityat.crypzee_cryptotrackingapp.SignUporsigninScreens.Signuppage
import com.adityat.crypzee_cryptotrackingapp.Viewmodel.MainViewModel
import com.adityat.crypzee_cryptotrackingapp.ui.theme.CrypzeeCryptoTrackingAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CrypzeeCryptoTrackingAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val controller: NavController = rememberNavController()
                    // Navigation(controller)
                    Dashboard(controller)
                }
            }
        }
    }
}

@Composable
fun Navigation(navController: NavController, padding: PaddingValues, viewModel: MainViewModel) {
    NavHost(
        navController = navController as NavHostController,
        startDestination = Screen.BottomScreens.topScreens.MarketCap.route,
        enterTransition = { EnterTransition.None },
        exitTransition = { ExitTransition.None },
        popEnterTransition = { EnterTransition.None },
        popExitTransition = { ExitTransition.None }
    ) {
        composable(Screen.entityScreen.SignUpHomeScreen.route) {
            SignUphomepage(navController)
        }
        composable(Screen.entityScreen.SignInScreen.route) {
            SignInPage(navController)
        }
        composable(Screen.entityScreen.SignUpScreen.route) {
            Signuppage(navController)
        }
        composable(Screen.BottomScreens.Wishlist.route) {
            WishList()
        }
        composable(Screen.BottomScreens.Home.route) {
            MarketCap(controller = navController, padding = padding , viewModel)
        }

        composable(Screen.BottomScreens.Explore.route) {
            Explore()
        }
        composable(Screen.BottomScreens.Settings.route) {
            Settings()
        }
        composable(Screen.BottomScreens.topScreens.MarketCap.route) {
            MarketCap(controller = navController, padding = padding, viewModel = viewModel)
        }
        composable(Screen.BottomScreens.topScreens.Gainers.route) {
            Gainer(controller = navController, padding = padding,viewModel)

        }
        composable(Screen.BottomScreens.topScreens.Losers.route) {
            Losers(controller = navController, padding = padding,viewModel)
        }

    }
}

