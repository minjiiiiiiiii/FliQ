package com.hongul.fliq.ui.minjiiiiii

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.Image
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.TextStyle
import com.hongul.fliq.R
import androidx.compose.foundation.layout.Arrangement



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChatScreen(onBackPress: () -> Unit) {
    val chatMessages = remember { mutableStateListOf<String>() }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "챗봇",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onBackPress) {
                        Icon(
                            painter = painterResource(id = R.drawable.mj_arrow_back_ios), // 아이콘 리소스 경로
                            contentDescription = "뒤로 가기",
                            tint = Color.Black
                        )
                    }
                },
                colors = TopAppBarDefaults.mediumTopAppBarColors(containerColor = Color(0xFFF0F0F0)) // 탑바 배경 색을 회색으로 설정
            )
        },
        content = { paddingValues ->
            Column(modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFF0F0F0)) // 배경 색을 탑바와 동일한 회색으로 설정
                .padding(paddingValues)) {
                // 박스들
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    Column { // 박스 수직으로 쌓기

                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Start
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.mj_ic_chatbot),
                                contentDescription = "Chatbot Icon",
                                modifier = Modifier
                                    .size(70.dp)
                                    .padding(end = 8.dp)
                            )

                            Box(
                                modifier = Modifier
                                    .fillMaxWidth(0.5f)
                                    .background(Color(0x4D7FBE85))
                                    .padding(8.dp)
                            ) {
                                Text(
                                    text = "안녕하세요.\n저는 홍열홍열입니다.",
                                    style = TextStyle(fontSize = 14.sp, color = Color.Black)
                                )
                            }
                        }

                        Spacer(modifier = Modifier.height(8.dp))

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.End // 오른쪽 정렬
                        ) {
                            // 박스를 내용 크기만큼만 설정
                            Box(
                                modifier = Modifier
                                    .background(Color.White)
                                    .padding(8.dp)
                            ) {
                                Text(
                                    text = "경력/직무 관련 Q&A",
                                    modifier = Modifier
                                        .wrapContentWidth(Alignment.End), // 텍스트를 오른쪽 정렬
                                    style = TextStyle(
                                        fontSize = 14.sp,
                                        color = Color.Black
                                    )
                                )
                            }
                        }
                        Spacer(modifier = Modifier.height(16.dp)) // 박스 사이 간격

                        Row( // 경력 직무 큐엔에이 answer
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Start
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.mj_ic_chatbot),
                                contentDescription = "Chatbot Icon",
                                modifier = Modifier
                                    .size(70.dp)
                                    .padding(end = 8.dp)
                            )

                            Box(
                                modifier = Modifier
                                    .fillMaxWidth(0.6f)
                                    .background(Color(0x4D7FBE85))
                                    .padding(8.dp)
                            ) {
                                Text(
                                    text = "웹/앱 UI를 개발하고 사용자 경험을 고려한\n프론트엔드 개발자입니다.",
                                    style = TextStyle(fontSize = 14.sp, color = Color.Black)
                                )
                            }
                        }
                        Spacer(modifier = Modifier.height(8.dp))
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Start
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.mj_ic_chatbot),
                                contentDescription = "Chatbot Icon",
                                modifier = Modifier
                                    .size(70.dp)
                                    .padding(end = 8.dp)
                            )

                            Box(
                                modifier = Modifier
                                    .fillMaxWidth(0.6f)
                                    .background(Color(0x4D7FBE85))
                                    .padding(8.dp)
                            ) {
                                Text(
                                    text = "React, Jetpack Compose 등을 활용하며,  API 연동과 협업에도 익숙합니다.",
                                    style = TextStyle(fontSize = 14.sp, color = Color.Black)
                                )
                            }
                        }
                        Spacer(modifier = Modifier.height(16.dp))
                        //sns 소개
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.End // 오른쪽 정렬
                        ) {
                            // 박스를 내용 크기만큼만 설정
                            Box(
                                modifier = Modifier
                                    .background(Color.White)
                                    .padding(8.dp)
                            ) {
                                Text(
                                    text = "SNS 소개",
                                    modifier = Modifier
                                        .wrapContentWidth(Alignment.End), // 텍스트를 오른쪽 정렬
                                    style = TextStyle(
                                        fontSize = 14.sp,
                                        color = Color.Black
                                    )
                                )
                            }
                        }
                        Spacer(modifier = Modifier.height(8.dp))

                        Row( // SNS answer
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Start
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.mj_ic_chatbot),
                                contentDescription = "Chatbot Icon",
                                modifier = Modifier
                                    .size(70.dp)
                                    .padding(end = 8.dp)
                            )

                            Box(
                                modifier = Modifier
                                    .fillMaxWidth(0.6f)
                                    .background(Color(0x4D7FBE85))
                                    .padding(8.dp)
                            ) {
                                Text(
                                    text = "kakaotalk : hongchu\ninstagram : @hongchu",
                                    style = TextStyle(fontSize = 14.sp, color = Color.Black)
                                )
                            }
                        }
                    }
                }


                Spacer(modifier = Modifier.weight(1f))

                // 카테고리 항목들
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                        .background(Color.White) // 하단 카테고리 바 배경을 흰색으로 설정
                ) {
                    // 첫 번째 카테고리 항목에만 이미지 추가
                    Image(
                        painter = painterResource(id = R.drawable.mj_ic_handbar), // 이미지 리소스 경로
                        contentDescription = "카테고리 이미지",
                        modifier = Modifier
                            .size(50.dp) // 이미지 크기 설정
                            .align(Alignment.CenterHorizontally) // 수평 중앙 정렬
                    )

                    // 텍스트 항목들
                    CategoryItem("경력/직무 관련 Q&A")
                    CategoryItem("포트폴리오 설명")
                    CategoryItem("SNS 소개")
                }
            }
        }
    )
}

@Composable
fun CategoryItem(text: String) {
    Column(modifier = Modifier.fillMaxWidth()) {
        // 텍스트
        Text(
            text = text,
            style = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.Bold, color = Color.Black),
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp) // 텍스트를 오른쪽으로 조금 이동시키기 위해 왼쪽 여백 추가
                .padding(top = 8.dp) // 이미지와 텍스트 사이의 여백
        )
    }
    Spacer(modifier = Modifier.height(20.dp)) // 각 항목 간의 간격
}

@Preview(showBackground = true)
@Composable
fun PreviewChatScreen() {
    ChatScreen(onBackPress = {})
}