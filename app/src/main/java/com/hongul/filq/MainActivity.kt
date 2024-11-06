package com.hongul.filq

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.rememberNavController
import com.hongul.filq.ui.home.HomeScreen
import com.hongul.filq.ui.theme.FilQTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FilQTheme(dynamicColor = false) {
                val navController = rememberNavController()
                HomeScreen() { }
            }
        }
    }
}
