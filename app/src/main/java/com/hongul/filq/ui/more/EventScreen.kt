@file:OptIn(ExperimentalMaterial3Api::class)

package com.hongul.filq.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun EventScreen(onBack: () -> Unit) {
    val selectedTabIndex = remember { mutableStateOf(0) } // 기본적으로 '이벤트' 탭 선택

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("이벤트/공지사항") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = "뒤로 가기")
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            // TabRow를 위한 구조
            TabRow(
                selectedTabIndex = selectedTabIndex.value,
                modifier = Modifier.fillMaxWidth()
            ) {
                // 이벤트 탭
                Tab(
                    selected = selectedTabIndex.value == 0,
                    onClick = { selectedTabIndex.value = 0 }, // 이벤트 탭 클릭 시 선택된 인덱스를 0으로 설정
                    text = { Text("이벤트") },
                    selectedContentColor = Color.Black,
                    unselectedContentColor = Color.Gray
                )
                // 공지사항 탭
                Tab(
                    selected = selectedTabIndex.value == 1,
                    onClick = { selectedTabIndex.value = 1 }, // 공지사항 탭 클릭 시 선택된 인덱스를 1으로 설정
                    text = { Text("공지사항") },
                    selectedContentColor = Color.Black,
                    unselectedContentColor = Color.Gray
                )
            }

            // 선택된 탭에 해당하는 밑줄
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                // 이벤트 탭 밑줄
                Box(
                    modifier = Modifier
                        .height(2.dp)
                        .background(if (selectedTabIndex.value == 0) Color.Black else Color.Gray)
                )
                // 공지사항 탭 밑줄
                Box(
                    modifier = Modifier
                        .height(2.dp)
                        .background(if (selectedTabIndex.value == 1) Color.Black else Color.Gray)
                )
            }

            // 탭에 따라 다른 내용 표시
            Spacer(modifier = Modifier.height(20.dp))
            when (selectedTabIndex.value) {
                0 -> {
                    Text("여기에 이벤트 내용이 들어갑니다.")
                }
                1 -> {
                    Text("여기에 공지사항 내용이 들어갑니다.")
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewEventScreen() {
    EventScreen(onBack = {})
}