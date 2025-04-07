package com.hongul.fliq.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.hongul.fliq.ui.navigation.BottomNavigation
import com.hongul.fliq.ui.navigation.NavigationGraph

@Composable
fun FliQApp() {
    val navController = rememberNavController()
    var showNavigation by rememberSaveable { mutableStateOf(true) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .safeDrawingPadding(),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        NavigationGraph(navController = navController) {
            showNavigation = it
        }

        if(showNavigation){
            BottomNavigation(navController = navController)
        }
    }
}