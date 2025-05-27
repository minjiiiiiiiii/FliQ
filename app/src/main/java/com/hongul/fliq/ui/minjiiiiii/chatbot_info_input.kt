package com.hongul.fliq.ui.minjiiiiii

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hongul.fliq.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Chatbot_info_input(
    onCompleteClick: () -> Unit = {},
    onBackPress: () -> Unit = {}
) {
    val titleColor = Color(0xFF125422)  // customGreen
    val customGreen = Color(0xFF125422)

    val questionList = remember { mutableStateListOf("", "", "") }
    val answerList = remember { mutableStateListOf("", "", "") }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "챗봇 생성",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onBackPress) {
                        Icon(
                            painter = painterResource(id = R.drawable.mj_arrow_back_ios),
                            contentDescription = "뒤로 가기",
                            tint = Color.Black
                        )
                    }
                }
            )
        },

        // 완료 버튼 - 스크롤 해도 고ㅇ정
        bottomBar = {
            Button(
                onClick = onCompleteClick,
                colors = ButtonDefaults.buttonColors(containerColor = customGreen),
                shape = RoundedCornerShape(5.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
                    .padding(horizontal = 16.dp, vertical = 8.dp)
            ) {
                Text("완료", fontSize = 18.sp, color = Color.White)
            }
        }
    ) { innerPadding ->

        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {

            // 질문, 답변 입력 받기 and 박스 추가시 스크롤 가능
            Column(
                modifier = Modifier
                    .weight(1f)
                    .verticalScroll(rememberScrollState()) // 스크롤 가능
                    .padding(16.dp)
            ) {
                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = "챗봇이 설명할 정보를\n추가해주세요.",
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp
                )

                Spacer(modifier = Modifier.height(30.dp))

                questionList.forEachIndexed { index, _ ->

                    Text("질문", fontWeight = FontWeight.Bold, fontSize = 16.sp, color = Color.Black)
                    Spacer(modifier = Modifier.height(5.dp))

                    // 질문 입력 박스
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Color(0xFFF2F2F2), RoundedCornerShape(5.dp)) // 배경색 EF2F2F2
                            .padding(horizontal = 16.dp, vertical = 12.dp)
                    ) {
                        BasicTextField(
                            value = questionList[index],
                            onValueChange = { questionList[index] = it },
                            textStyle = TextStyle(color = Color.Black, fontSize = 14.sp),
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            if (questionList[index].isEmpty()) {
                                Text(
                                    text = "질문을 입력해 주세요",
                                    style = TextStyle(color = Color.Gray.copy(alpha = 0.6f)),
                                    fontSize = 14.sp,
                                    fontWeight = FontWeight.Normal
                                )
                            }
                            it() // 사용자가 입력하는 부분
                        }
                    }


                    Spacer(modifier = Modifier.height(5.dp))

                    // 답변 입력 박스
                    BasicTextField(
                        value = answerList[index],
                        onValueChange = { answerList[index] = it },
                        textStyle = TextStyle(color = Color.Black, fontSize = 14.sp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Color(0xFFF2F2F2), RoundedCornerShape(5.dp))
                            .padding(horizontal = 16.dp, vertical = 12.dp)
                    ) {
                        if (answerList[index].isEmpty()) {
                            Text(
                                text = "질문의 답변을 입력해 주세요",
                                style = TextStyle(color = Color.Gray.copy(alpha = 0.6f)),
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Normal
                            )
                        }
                        it()
                    }

                    Spacer(modifier = Modifier.height(25.dp))
                }

                // ic_add 누르면 질문+답변 박스 추가
                IconButton(
                    onClick = {
                        questionList.add("")
                        answerList.add("")
                    },
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.mj_ic_add),
                        contentDescription = "추가",
                        tint = customGreen
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ChatbotInfoInputPreview() {
    Chatbot_info_input()
}
