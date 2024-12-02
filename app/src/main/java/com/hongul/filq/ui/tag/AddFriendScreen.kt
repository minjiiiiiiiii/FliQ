package com.hongul.filq.ui.tag

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hongul.filq.R
import com.hongul.filq.model.Avatar
import com.hongul.filq.model.BusinessCard
import com.hongul.filq.model.SNS

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddFriendScreen(
    name: String,
    tags: String,
    onBackPress: () -> Unit = {} // 뒤로 가기 버튼 클릭 이벤트
) {
    val titleColor = Color(0xFF125422)
    val contentColor = Color.Black

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "") },
                navigationIcon = {
                    IconButton(onClick = onBackPress) {
                        Icon(
                            painter = painterResource(id = R.drawable.arrow_back_ios), // 뒤로 가기 아이콘
                            contentDescription = "뒤로 가기",
                            tint = Color.Black
                        )
                    }
                },
                colors = TopAppBarDefaults.smallTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.background
                )
            )
        },
        content = { innerPadding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Column(
                    modifier = Modifier.weight(1f),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    // 명함 사진
                    Image(
                        painter = painterResource(id = R.drawable.ic_hongchuping),
                        contentDescription = "Profile Image",
                        modifier = Modifier
                            .fillMaxWidth()
                            .aspectRatio(16f / 9f)
                            .padding(bottom = 16.dp)
                    )

                    // 그림자 있는 흰 박스
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .shadow(elevation = 4.dp, shape = RoundedCornerShape(16.dp)) // 그림자 추가
                            .background(
                                color = Color.White,
                                shape = RoundedCornerShape(16.dp)
                            ) // 흰 배경 추가
                            .padding(16.dp)
                    ) {
                        Column(
                            modifier = Modifier.fillMaxWidth()
                                .verticalScroll(rememberScrollState())
                        ) {
                            // Name
                            Text(
                                text = "$name",
                                fontWeight = FontWeight.Bold,
                                fontSize = 24.sp,
                                color = contentColor,
                                textAlign = TextAlign.Center,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(bottom = 16.dp)
                            )
                            // 추가하기 버튼
                            Column(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                // 아이콘 버튼
                                Icon(
                                    painter = painterResource(id = R.drawable.addfriend),
                                    contentDescription = "추가하기 아이콘",
                                    tint = Color(0xFF125422), // 아이콘 색상
                                    modifier = Modifier
                                        .size(25.dp) // 아이콘 크기 설정
                                        .clickable { /* 추가하기 버튼 클릭 로직 */ } // 클릭 이벤트 처리
                                )
                                Spacer(modifier = Modifier.height(4.dp))

                                // 텍스트
                                Text(
                                    text = "추가하기",
                                    fontSize = 13.sp, // 텍스트 크기 확대
                                    fontWeight = FontWeight.Bold,
                                    color = Color(0xFF125422) // 텍스트 색상
                                )
                            }

                            Divider(
                                color = Color.LightGray,
                                thickness = 1.dp,
                                modifier = Modifier.padding(vertical = 8.dp)
                            )

                            // SNS
                            Text(
                                text = "SNS",
                                fontWeight = FontWeight.Bold,
                                fontSize = 16.sp,
                                color = titleColor,
                                modifier = Modifier.padding(bottom = 8.dp)
                            )
                            Column(
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                // Instagram
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    modifier = Modifier.padding(bottom = 8.dp)
                                ) {
                                    Image(
                                        painter = painterResource(id = R.drawable.ic_insta),
                                        contentDescription = "Instagram Icon",
                                        modifier = Modifier.size(24.dp)
                                    )
                                    Spacer(modifier = Modifier.width(8.dp))
                                    Text(
                                        text = "@hongchuchuchu",
                                        fontSize = 14.sp,
                                        color = contentColor
                                    )
                                }
                                // Kakao
                                Row(
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Image(
                                        painter = painterResource(id = R.drawable.ic_kakao),
                                        contentDescription = "Kakao Icon",
                                        modifier = Modifier.size(24.dp)
                                    )
                                    Spacer(modifier = Modifier.width(8.dp))
                                    Text(
                                        text = "hongchuchu",
                                        fontSize = 14.sp,
                                        color = contentColor
                                    )
                                }
                            }
                            Spacer(modifier = Modifier.height(8.dp))

                            // 태그
                            Text(
                                text = "태그",
                                fontWeight = FontWeight.Bold,
                                fontSize = 16.sp,
                                color = titleColor,
                                modifier = Modifier.padding(bottom = 8.dp)
                            )
                            Text(
                                text = tags,
                                fontSize = 14.sp,
                                color = contentColor,
                                modifier = Modifier.padding(bottom = 16.dp)
                            )

                            // QR 코드
                            Text(
                                text = "QR 코드",
                                fontWeight = FontWeight.Bold,
                                fontSize = 16.sp,
                                color = titleColor,
                                modifier = Modifier.padding(bottom = 8.dp)
                            )
                            Image(
                                    painter = painterResource(id = R.drawable.ic_qr),
                                    contentDescription = "QR Code",
                                    modifier = Modifier.size(100.dp)
                            )
                        }
                    }
                }
            }
        }
    )
}



@Preview(showBackground = true, name = "AddFriendScreen Preview")
@Composable
fun AddFriendScreenPreview() {
    AddFriendScreen(
        name = "홍츄핑",
        tags = "React, Node.js, TypeScript",
        onBackPress = { /* Back logic */ }
    )
}





