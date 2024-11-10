package com.hongul.filq.ui

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.hongul.filq.R
import com.hongul.filq.ui.home.HomeScreen
import com.hongul.filq.ui.home.StickerChangeRoute
import com.hongul.filq.ui.home.StickerChangeScreen

sealed class NavItem(val route: String, val title: String, @DrawableRes val icon: Int) {
    data object Home : NavItem("home", "내 명함", R.drawable.ic_nav_home)
    data object Contact : NavItem("contact", "연락처", R.drawable.ic_nav_contact)
    data object QR : NavItem("qr", "QR 공유", R.drawable.ic_nav_qr)
    data object More : NavItem("more", "더보기", R.drawable.ic_nav_more)
}

@Composable
fun NavigationGraph(navController: NavHostController) {
    Column(Modifier.fillMaxSize()) {
        // TODO: serializable 클래스로 루트 교체
        NavHost(
            navController = navController,
            startDestination = NavItem.Home.route,
            modifier = Modifier.weight(1f)
        ) {
            composable(NavItem.Home.route) {
                HomeScreen(navigator = navController)
            }
            composable(NavItem.Contact.route) {
                PlaceHolder(it.destination.route!!)
            }
            composable(NavItem.QR.route) {
                PlaceHolder(it.destination.route!!)
            }
            composable(NavItem.More.route) {
                PlaceHolder(it.destination.route!!)
            }
            composable<StickerChangeRoute> {
                val route = it.toRoute<StickerChangeRoute>()
                StickerChangeScreen(route.cardId)
            }
        }
        BottomNavigation(navController = navController)
    }
}

@Composable
private fun PlaceHolder(title: String) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        content = {
            Text(text = title)
        }
    )
}