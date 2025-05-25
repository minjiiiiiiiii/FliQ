package com.hongul.fliq.ui.customize.page

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OrganizationInfoPage(onBack: () -> Unit = {}, onNext: () -> Unit) {
    val progress = 0.5f
    val errorMessage = remember { mutableStateOf("") }
    val organization = remember { mutableStateOf("") }
    val department = remember { mutableStateOf("") }
    val position = remember { mutableStateOf("") }

    Scaffold() { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 24.dp),
            horizontalAlignment = Alignment.Start
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp)
                    .height(14.dp)
                    .clip(RoundedCornerShape(50))
            ) {
                LinearProgressIndicator(
                    progress = progress,
                    modifier = Modifier.fillMaxSize(),
                    color = Color(0xFF9CD2A1)
                )
            }

            Text(
                text = "나의 소속을\n입력해 주세요.",
                fontSize = 30.sp,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.padding(vertical = 20.dp)
            )

            Column(modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = buildAnnotatedString {
                        append("기업 또는 단체명 ")
                        withStyle(style = SpanStyle(color = Color.Red)) {
                            append("*")
                        }
                    },
                    modifier = Modifier.padding(start = 5.dp, bottom = 4.dp),
                    fontWeight = FontWeight.Bold
                )

                OutlinedTextField(
                    value = organization.value,
                    onValueChange = { organization.value = it },
                    placeholder = { Text("예) 계명대학교") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp)
                        .background(
                            Color.Gray.copy(alpha = 0.1f),
                            RoundedCornerShape(8.dp)
                        ),
                    shape = RoundedCornerShape(8.dp),
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = Color.Transparent,
                        unfocusedContainerColor = Color.Transparent,
                        disabledContainerColor = Color.Transparent,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        disabledIndicatorColor = Color.Transparent
                    )
                )
                Text(
                    text = "${organization.value.length}/20",
                    modifier = Modifier.padding(start = 360.dp, top = 0.dp),
                    style = androidx.compose.ui.text.TextStyle(fontSize = 12.sp, color = Color.Gray)
                )
            }

            Spacer(modifier = Modifier.height(8.dp))


            Column(modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = buildAnnotatedString {
                        append("부서 / 직책")
                        withStyle(style = SpanStyle(color = Color.Red)) {
                            append("*")
                        }
                    },
                    modifier = Modifier.padding(start = 5.dp, bottom = 4.dp),
                    fontWeight = FontWeight.Bold
                )
                OutlinedTextField(
                    value = department.value,
                    onValueChange = { department.value = it },
                    placeholder = { Text("예 ) 컴퓨터공학과 / 5723483") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp)
                        .background(
                            Color.Gray.copy(alpha = 0.1f),
                            RoundedCornerShape(8.dp)
                        ),
                    shape = RoundedCornerShape(8.dp),
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = Color.Transparent,
                        unfocusedContainerColor = Color.Transparent,
                        disabledContainerColor = Color.Transparent,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        disabledIndicatorColor = Color.Transparent
                    )
                )
                Text(
                    text = "${department.value.length}/20",
                    modifier = Modifier.padding(start = 360.dp, top = 0.dp),
                    style = androidx.compose.ui.text.TextStyle(fontSize = 12.sp, color = Color.Gray)
                )
            }

            Spacer(modifier = Modifier.height(8.dp))


            Column(modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = buildAnnotatedString {
                        append("추가 직책")
                        withStyle(style = SpanStyle(color = Color.Red)) {
                            append("*")
                        }
                    },
                    modifier = Modifier.padding(start = 5.dp, bottom = 4.dp),
                    fontWeight = FontWeight.Bold
                )

                OutlinedTextField(
                    value = position.value,
                    onValueChange = { position.value = it },
                    placeholder = { Text("예 ) 기획부장") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp)
                        .background(
                            Color.Gray.copy(alpha = 0.1f),
                            RoundedCornerShape(8.dp)
                        ),
                    shape = RoundedCornerShape(8.dp),
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = Color.Transparent,
                        unfocusedContainerColor = Color.Transparent,
                        disabledContainerColor = Color.Transparent,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        disabledIndicatorColor = Color.Transparent
                    )
                )
                Text(
                    text = "${position.value.length}/20",
                    modifier = Modifier.padding(start = 360.dp, top = 0.dp),
                    style = androidx.compose.ui.text.TextStyle(fontSize = 12.sp, color = Color.Gray)
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            if (errorMessage.value.isNotEmpty()) {
                Text(
                    text = errorMessage.value,
                    color = Color.Red,
                    fontSize = 14.sp,
                    modifier = Modifier.padding(vertical = 8.dp)
                )
            }
            Spacer(modifier = Modifier.weight(1f))
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                contentAlignment = Alignment.BottomCenter
            ) {
                Button(
                    onClick = { onNext() },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp),
                    shape = RoundedCornerShape(8.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF125422))
                ) {
                    Text(text = "다음", color = Color.White, fontSize = 16.sp)
                }
            }
        }
    }
}

