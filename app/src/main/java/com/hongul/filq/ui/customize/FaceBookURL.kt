package com.hongul.filq.ui.customize

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FaceBookURL() {
    val titlePadding = 121.dp

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween // Distribute space between items
                    ) {
                        Text(
                            text = "Facebook",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = Color.Black,
                            modifier = Modifier.padding(start = titlePadding) // Left padding
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

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(80.dp)
                    .padding(top = 16.dp)
                    .clip(RoundedCornerShape(50)) // Box의 모서리를 둥글게 설정
            ) {
            }

            val FacebookUrl = remember { mutableStateOf("") }

            Column(modifier = Modifier.fillMaxWidth()) {
                // 기업 또는 단체명 입력 필드
                OutlinedTextField(
                    value = FacebookUrl.value,
                    onValueChange = { FacebookUrl.value = it },
                    placeholder = { Text("SNS 주소를 입력하세요.") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp)
                        .background(Color.Gray.copy(alpha = 0.1f)), // 배경 색상 설정
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = Color.Transparent, // 테두리 없애기
                        unfocusedBorderColor = Color.Transparent // 테두리 없애기
                    )
                )
                Text(text = "${FacebookUrl.value.length}/100",
                    modifier = Modifier.padding(start = 320.dp, top = 0.dp),
                    style = androidx.compose.ui.text.TextStyle(fontSize = 13.sp, color = Color.Gray)
                )
            }

            Spacer(modifier = Modifier.height(8.dp))



            // 다음 버튼
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(bottom = 16.dp),
                contentAlignment = Alignment.BottomCenter // 버튼을 화면 하단에 정렬
            ) {
                Button(
                    onClick = { /* 다음 버튼 클릭 로직 */ },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp),
                    shape = RoundedCornerShape(5.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF125422))
                ) {
                    Text(text = "등록", color = Color.White, fontSize = 16.sp)
                }
            }
        }
    }
}
