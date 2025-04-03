package com.hongul.fliq.ui.navigation

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.hongul.fliq.R
import com.hongul.fliq.ui.navigation.styles.BottomNavigationStyles.Colors
import com.hongul.fliq.ui.navigation.styles.BottomNavigationStyles.Modifiers.container
import com.hongul.fliq.ui.navigation.styles.BottomNavigationStyles.Modifiers.item

sealed class NavItem(
    val title: String,
    val route: String,
    @DrawableRes val icon: Int,
) {
    object Home: NavItem("내 명함", "home", R.drawable.ic_nav_home)
    object Contact: NavItem("연락처", "contact", R.drawable.ic_nav_contact)
    object Search: NavItem("검색", "search", R.drawable.ic_nav_search)
    object More: NavItem("더보기", "more", R.drawable.ic_nav_more)
}

@Composable
fun BottomNavigation(navController: NavHostController) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()

    val items = listOf<NavItem>(
        NavItem.Home,
        NavItem.Contact,
        NavItem.Search,
        NavItem.More
    )

    Row(
        modifier = Modifier.container(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        for(item in items) {
            BottomNavigationItem(
                icon = item.icon,
                title = item.title,
                isSelected = item.route == navBackStackEntry?.destination?.route
            ) {
                navController.navigate(item.route) {
                    navController.graph.startDestinationRoute?.let {
                        popUpTo(it) { saveState = true }
                    }
                    launchSingleTop = true
                    restoreState = true
                }
            }
        }
    }
}

@Composable
fun BottomNavigationItem(
    @DrawableRes icon: Int,
    title: String,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    val baseColor = if (isSelected) Colors.selected else Colors.unselected

    Column(
        modifier = Modifier.item(onClick),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            ImageVector.vectorResource(id = icon),
            contentDescription = title,
            modifier = Modifier.scale(3f / 4f),
            tint = baseColor
        )
        Text(
            title,
            fontSize = 12.sp,
            fontWeight = FontWeight.SemiBold,
            color = baseColor
        )
    }
}