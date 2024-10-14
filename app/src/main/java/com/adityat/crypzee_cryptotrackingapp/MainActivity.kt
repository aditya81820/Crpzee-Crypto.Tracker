package com.adityat.crypzee_cryptotrackingapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.adityat.crypzee_cryptotrackingapp.MainUI.CoinDescription
import com.adityat.crypzee_cryptotrackingapp.MainUI.Dashboard
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
                    val viewModel : MainViewModel = viewModel()
                    val controller: NavController = rememberNavController()

                     Navigation(controller, viewModel)
                    //Dashboard(navController, viewModel)
                }
            }
        }
    }
}

@Composable
fun Navigation(navController: NavController, viewModel: MainViewModel) {

    NavHost(
        navController = navController as NavHostController,
        startDestination = Screen.entityScreen.SignUpHomeScreen.route,
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
        composable(Screen.entityScreen.Dashboard.route) {
            Dashboard(navController, viewModel)
        }

        composable(Screen.entityScreen.SignUpScreen.route) {
            Signuppage(navController)
        }
        composable(
            route = "${Screen.entityScreen.CoinDescription.route}/{coinId}",
            arguments = listOf(navArgument("coinId") { type = NavType.StringType })
        ) { backStackEntry ->
            val coinId = backStackEntry.arguments?.getString("coinId")
            CoinDescription(coinId , viewModel)
        }


    }
}

