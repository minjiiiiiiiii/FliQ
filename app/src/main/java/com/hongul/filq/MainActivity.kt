package com.hongul.filq

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.hongul.filq.ui.BottomNavigation
import com.hongul.filq.ui.NavigationGraph
import com.hongul.filq.ui.theme.FilQTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FilQTheme(dynamicColor = false) {
                val navController = rememberNavController()

                // TODO: 하위 composable이 제대로 스크롤 가능 하게끔 고치기
                Box(Modifier.fillMaxSize()) {
                    Box(Modifier.padding(bottom = 80.dp).fillMaxSize()) {
                        NavigationGraph(navController = navController)
                    }
                    BottomNavigation(navController = navController)
                }
            }
        }
    }
}
