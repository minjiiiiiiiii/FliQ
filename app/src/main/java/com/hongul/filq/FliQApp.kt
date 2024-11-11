package com.hongul.filq

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.hongul.filq.ui.NavigationGraph

@Composable
fun FliQApp(navController: NavHostController = rememberNavController()) {
    NavigationGraph(navController = navController)
}