@file:OptIn(ExperimentalMaterial3Api::class)

package com.hongul.filq.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.mutableStateOf
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


@Composable
fun AppScreen() {
    val currentScreen = remember { mutableStateOf("더보기") }

    when (currentScreen.value) {
        "더보기" -> MoreScreen(
            onNavigateToMyPage = { currentScreen.value = "마이페이지" },
            onNavigateToEvent = { currentScreen.value = "이벤트" },
            onNavigateToInquiry = { currentScreen.value = "1:1 문의" },
            onNavigateToSettings = { currentScreen.value = "설정" } // 설정 화면 추가
        )
        "마이페이지" -> MyPageScreen(onBack = { currentScreen.value = "더보기" })
        "이벤트" -> EventScreen(onBack = { currentScreen.value = "더보기" })
        "1:1 문의" -> InquiryScreen(onBack = { currentScreen.value = "더보기" })
        "설정" -> SettingsScreen(onBack = { currentScreen.value = "더보기" }) // SettingsScreen 추가
    }
}
