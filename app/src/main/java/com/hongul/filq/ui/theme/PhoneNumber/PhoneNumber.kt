package com.hongul.filq.ui.theme.PhoneNumber

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hongul.filq.R


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PhoneNumberScreen(modifier: Modifier = Modifier) {
    var shouldUpdatedContactScreen by remember { mutableStateOf(true) } // contact 화면 보여줄지 여부

    Scaffold(
        modifier = Modifier.fillMaxSize()
            .padding(vertical = 20.dp),
        topBar = {
            TopAppBar(
                title = { Text("명함", fontSize = 20.sp, fontWeight = FontWeight.Bold,
                    textDecoration = TextDecoration.Underline) },
                navigationIcon = {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.KeyboardArrowLeft,
                        contentDescription = null
                    )
                }

            )
        }
    ) { innerPadding ->
        Surface(modifier = Modifier.padding(innerPadding)) { // 기본 배경
            if (shouldUpdatedContactScreen) {
                UpdatedContactScreen(onContinueClicked = { shouldUpdatedContactScreen = false })
            } else {
                CategoryScreen()
            }
        }
    }
}

@Composable
fun UpdatedContactScreen(
    onContinueClicked: () -> Unit, // 람다 함수. 버튼 클릭 시 수행될 작업을 외부에서 정의할 수 있도록 함
    modifier: Modifier = Modifier
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(vertical = 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally, // 요소들이 수평으로 가운데 배치
    ) {
        var selectedCategory by remember { mutableStateOf(0) } // 카테고리 선택, 기본값: '개인' 버튼

        Row {
            Button(
                onClick = { selectedCategory = 0 },
                colors = ButtonDefaults.buttonColors(
                    if (selectedCategory == 0) Color(0xFF125422) else
                        Color.Transparent
                )
            ) { // 클릭하면 녹색, 아니면 투명색
                Text("개인", color = if (selectedCategory == 0) Color.White else Color.Black)
            }
            Spacer(modifier = Modifier.padding(10.dp)) // 버튼 사이 여백

            Button(
                onClick = { selectedCategory = 1 },
                colors = ButtonDefaults.buttonColors(
                    if (selectedCategory == 1) Color(0xFF125422) else
                        Color.Transparent
                )
            ) { Text("업무", color = if (selectedCategory == 1) Color.White else Color.Black) }
            Spacer(modifier = Modifier.padding(50.dp)) // 버튼 사이 여백

            Button(colors = ButtonDefaults.buttonColors(Color.Transparent),
                onClick = onContinueClicked)
            { Text("+", fontSize = 25.sp, color = Color.Black) }
        }
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally, // 요소들이 수평으로 가운데 배치
        verticalArrangement = Arrangement.Center // 요소들이 수직으로 가운데 배치
    ) {
        Image(
            painter = painterResource(id = R.drawable.daemori_image),
            contentDescription = null,
            modifier = Modifier.size(200.dp)
        )

        Text(
            "지인의 명함이나 연락처를 추가하여\n편리하게 관리하세요.",
            fontSize = 12.sp, color = Color.Gray,
            textAlign = TextAlign.Center, // 텍스트 가운데 정렬
            modifier = Modifier.padding(16.dp)
        )

        Button(
            onClick = { /* 버튼 클릭 시 처리 */ },
            modifier = Modifier.fillMaxWidth() // 버튼을 가로로 꽉 채우기
                .height(50.dp), // 버튼 굵기 조정
            shape = RoundedCornerShape(12.dp), // 모서리 각지게 하기 (12dp)
            colors = ButtonDefaults.buttonColors(Color(0xFF125422))
        ) {Text("연락처 추가하기") }
    }
}

@Composable
fun CategoryScreen(modifier: Modifier = Modifier) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)) {
        Text(
            "카테고리 추가", fontSize = 18.sp,
            modifier = Modifier.padding(16.dp)
        )

        Button(
            onClick = { /* 버튼 클릭 시 처리 */ },
            modifier = Modifier.fillMaxWidth()
                .padding(vertical = 5.dp, horizontal = 16.dp),
            shape = RoundedCornerShape(12.dp), // 모서리 각지게 하기 (12dp)
            colors = ButtonDefaults.buttonColors(Color.Gray)
        ) {
            Row(horizontalArrangement = Arrangement.Start,  // 왼쪽 정렬
            modifier = Modifier.fillMaxWidth() // 버튼 너비 채우기
        ) { Text("카테고리명 입력", color = Color.Black, fontSize = 15.sp) }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun UpdatedContactPreview() {
    UpdatedContactScreen(onContinueClicked = {})
}

@Preview(showBackground = true)
@Composable
fun CategoryPreview() {
    CategoryScreen()
}

@Preview(showBackground = true)
@Composable
fun PhoneNumberPreview() {
    PhoneNumberScreen(Modifier.fillMaxSize())
}