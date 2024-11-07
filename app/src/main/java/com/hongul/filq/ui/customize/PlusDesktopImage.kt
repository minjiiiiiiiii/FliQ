package com.hongul.filq.ui.customize

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hongul.filq.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PlusDesktopImage() {
    val titlePadding = 90.dp

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "배경화면 선택하기",
                            fontSize = 18.sp,
                            color = Color.Black,
                            modifier = Modifier.padding(start = titlePadding)
                        )
                    }
                },
                navigationIcon = {
                    IconButton(onClick = { /* 뒤로 가기 클릭 로직 */ }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "뒤로 가기"
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.White)
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .padding(horizontal = 24.dp),
            horizontalAlignment = Alignment.Start
        ) {
            Spacer(modifier = Modifier.height(150.dp))
            Column(modifier = Modifier.fillMaxWidth()) {
                // 이미지 표시 (add_business_card.png)
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(300.dp)
                        .padding(vertical = 16.dp), // 상하 여백 추가
                    contentAlignment = Alignment.Center
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.logo_business_card),
                        contentDescription = "Add Business Card",
                        modifier = Modifier
                            .fillMaxSize() // 화면 크기에 맞게 이미지 확장
                            .clip(RoundedCornerShape(8.dp)) // 이미지의 모서리를 둥글게 설정
                    )
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            // 다음 버튼
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(bottom = 16.dp),
                contentAlignment = Alignment.BottomCenter
            ) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp), // Space between buttons
                    modifier = Modifier
                        .fillMaxWidth() // Ensure Row takes up full width
                        .align(Alignment.BottomCenter) // Align Row at the bottom
                ) {
                    Button(
                        onClick = { /* 다음 버튼 클릭 로직 */ },
                        modifier = Modifier
                            .weight(1f) // Equal width for each button
                            .height(50.dp),
                        shape = RoundedCornerShape(5.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF125422))
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_camera),
                            contentDescription = null,
                        )
                        Text(text = " 사진첩에서 찾기", color = Color.White, fontSize = 16.sp)
                    }

                    Button(
                        onClick = { /* 다음 버튼 클릭 로직 */ },
                        modifier = Modifier
                            .weight(1f) // Equal width for each button
                            .height(50.dp),
                        shape = RoundedCornerShape(5.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF125422))
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.photo),
                            contentDescription = null,
                        )
                        Text(text = " 기본이미지 사용", color = Color.White, fontSize = 16.sp)
                    }
                }
            }
        }
    }
}
