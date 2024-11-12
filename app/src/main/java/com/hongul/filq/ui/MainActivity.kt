package com.hongul.filq.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.ui.Modifier
import com.hongul.filq.FliQApp
import com.hongul.filq.ui.theme.FilQTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        setContent {
            FilQTheme(dynamicColor = false) {
                Box(Modifier.safeDrawingPadding()) {
                    FliQApp()
                }
            }
        }
    }
}
