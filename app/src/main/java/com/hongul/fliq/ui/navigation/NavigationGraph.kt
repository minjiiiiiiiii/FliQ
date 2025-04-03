package com.hongul.fliq.ui.navigation

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.hongul.fliq.ui.home.HomeScreen

@Composable
fun ColumnScope.NavigationGraph(
    navController: NavHostController,
    showNavigation: (Boolean) -> Unit
) {
    NavHost(
        navController = navController,
        startDestination = NavItem.Home.route,
        modifier = Modifier.weight(1f)
    ) {
        composable(NavItem.Home.route) {
            showNavigation(true)
            HomeScreen()
        }
        composable(NavItem.Contact.route) {
            showNavigation(true)
        }
        composable(NavItem.Search.route) {
            showNavigation(true)
        }
        composable(NavItem.More.route) {
            showNavigation(true)
        }
    }
}