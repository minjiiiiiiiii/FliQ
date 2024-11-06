package com.hongul.filq.ui

import androidx.annotation.DrawableRes
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.hongul.filq.ui.home.HomeScreen

sealed class NavItem(val route: String, @DrawableRes val iconUnselected: Int, @DrawableRes val iconSelected: Int) {
    data object Home : NavItem("home")
}

@Composable
fun NavigationGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = NavItem.Home.route) {
        composable(NavItem.Home.route) {
            HomeScreen()
        }
    }
}