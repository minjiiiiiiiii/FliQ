package com.hongul.filq

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import com.hongul.filq.ui.NavigationGraph
import com.hongul.filq.ui.theme.FilQTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FilQTheme(dynamicColor = false) {
                NavigationGraph(navController = rememberNavController())
            }
        }
    }
}
