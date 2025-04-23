package com.hongul.fliq.ui.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.Ease
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.hongul.fliq.ui.home.pages.CardShareScreen
import com.hongul.fliq.ui.home.pages.HomeScreen

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
            HomeScreen(navigator = navController)
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
        composable(
            "share",
            enterTransition = {
                fadeIn(
                    animationSpec = tween(
                        500,
                        easing = Ease
                    )
                ) + slideIntoContainer(
                    animationSpec = tween(
                        500,
                        easing = Ease
                    ),
                    towards = AnimatedContentTransitionScope.SlideDirection.Up
                )
            },
            exitTransition = {
                fadeOut(
                    animationSpec = tween(
                        500,
                        easing = Ease
                    )
                ) + slideOutOfContainer(
                    animationSpec = tween(
                        500,
                        easing = Ease
                    ),
                    towards = AnimatedContentTransitionScope.SlideDirection.Down
                )
            }
        ) {
            showNavigation(false)
            CardShareScreen(navigator = navController)
        }
    }
}