package com.hongul.filq

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.hongul.filq.ui.theme.PhoneNumber.PhoneNumberScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PhoneNumberScreen()
        }
    }
}