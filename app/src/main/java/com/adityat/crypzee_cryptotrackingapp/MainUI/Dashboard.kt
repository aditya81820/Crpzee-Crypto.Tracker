@file:Suppress("UNUSED_EXPRESSION")

package com.adityat.crypzee_cryptotrackingapp.MainUI

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.adityat.crypzee_cryptotrackingapp.Navigation
import com.adityat.crypzee_cryptotrackingapp.Screen
import com.adityat.crypzee_cryptotrackingapp.Viewmodel.MainViewModel
import com.adityat.crypzee_cryptotrackingapp.screenInBottom
import com.adityat.crypzee_cryptotrackingapp.screenIntop

@Composable
fun Dashboard(controller: NavController) {
    val navBackStackEntry by controller.currentBackStackEntryAsState()
    val currentRoute =
        navBackStackEntry?.destination?.route ?: Screen.BottomScreens.Home.bRoute // Default to Home

    val viewModel: MainViewModel = viewModel()
    val currentScreen = remember {
        viewModel.currentScreen.value
    }
    val title = remember {
        mutableStateOf(currentScreen.title)
    }
    val bottombar: @Composable () -> Unit = {

        BottomNavigation(
            Modifier.wrapContentSize(),
            backgroundColor = MaterialTheme.colorScheme.primary
        ) {
            screenInBottom.forEach { item ->
                val isSelected =
                    (currentRoute == item.bRoute || item == Screen.BottomScreens.Home && (currentRoute == Screen.BottomScreens.topScreens.MarketCap.troute || currentRoute == Screen.BottomScreens.topScreens.Gainers.troute || currentRoute == Screen.BottomScreens.topScreens.Losers.troute))
                val tint = if (isSelected) Color(0xff4F75FF) else MaterialTheme.colorScheme.secondary
                BottomNavigationItem(
                    selected = currentRoute == item.bRoute,
                    onClick = {
                        controller.navigate(item.bRoute)
                        title.value = item.bTitle
                    },
                    icon = {
                        Icon(imageVector = item.icon, contentDescription = "", tint = tint)

                    },
                    selectedContentColor = Color.White,
                    unselectedContentColor = Color.Black

                )
            }
        }

    }
    Scaffold(
        backgroundColor = MaterialTheme.colorScheme.primary,
        topBar = {
            Column(modifier = Modifier.fillMaxWidth()) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(text = title.value, fontSize = 26.sp)
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = "",
                            modifier = Modifier.size(28.dp)
                        )
                    }
                }
                if (currentRoute == Screen.BottomScreens.Home.bRoute || currentRoute == Screen.BottomScreens.topScreens.MarketCap.troute ||
                    currentRoute == Screen.BottomScreens.topScreens.Gainers.troute ||
                    currentRoute == Screen.BottomScreens.topScreens.Losers.troute
                ) {
                    BottomNavigation(
                        Modifier.wrapContentSize(),
                        backgroundColor = MaterialTheme.colorScheme.primary
                    ) {
                        screenIntop.forEach { item ->
                            val isSelected =
                                (currentRoute == item.troute || currentRoute == Screen.BottomScreens.Home.bRoute && item == Screen.BottomScreens.topScreens.MarketCap)
                            val color = if (isSelected) Color(0xff4F75FF) else MaterialTheme.colorScheme.secondary
                            BottomNavigationItem(
                                selected = currentRoute == item.troute,
                                onClick = {
                                    controller.navigate(item.troute)
                                },
                                icon = {
                                    Text(text = item.ttitile, color = color)

                                },
                                selectedContentColor = Color.White,
                                unselectedContentColor = Color.Black

                            )
                        }
                    }
                }
            }


        },
        bottomBar = bottombar
    ) {
        Navigation(navController = controller, padding = it,viewModel)
    }
}