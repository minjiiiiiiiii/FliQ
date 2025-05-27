package com.hongul.fliq.ui.ddu111

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateOffsetAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hongul.fliq.R

@Composable
fun IconScreen() {
    // 선택된 탭 상태를 관리하는 변수
    var selectedTab by remember { mutableStateOf("아이콘") } // 기본 탭을 '아이콘'으로 설정
    var selectedIcon by remember { mutableStateOf(" ") } // 선택된 아이콘(이모지)
    var iconPosition by remember { mutableStateOf(Offset(0f, 0f)) } // 이모지의 위치를 관리

    // 이모지 위치 애니메이션 처리
    val animatedPosition by animateOffsetAsState(
        targetValue = iconPosition,
        animationSpec = tween(durationMillis = 300, easing = FastOutSlowInEasing) // 부드러운 애니메이션 적용
    )

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // 명함 카드 영역
        Spacer(modifier = Modifier.height(100.dp))  // 상단에 여백 추가
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .padding(horizontal = 50.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            shape = RoundedCornerShape(12.dp) // 카드의 모서리 둥글게 설정
        ) {
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically,  // 세로 중앙 정렬
                horizontalArrangement = Arrangement.Start // 왼쪽 정렬
            ) {
                Column(
                    modifier = Modifier.weight(1f), // 가로 영역을 분할하는데 1f로 할당
                    verticalArrangement = Arrangement.Center, // 수직 중앙 정렬
                ) {
                    // 명함의 내용 텍스트
                    Text(
                        text = "홍얼홍얼",
                        fontSize = 16.sp,
                        modifier = Modifier.align(Alignment.Start)
                    )
                    Text(
                        text = "+82)10.0000.0000",
                        fontSize = 16.sp,
                        modifier = Modifier.align(Alignment.Start)
                    )
                    Text(
                        text = "xxx@stu.kmu.ac.kr",
                        fontSize = 16.sp,
                        modifier = Modifier.align(Alignment.Start)
                    )
                    Text(
                        text = "Wishlist _ can't be blue",
                        fontSize = 16.sp,
                        modifier = Modifier.align(Alignment.Start)
                    )
                }

                // 선택된 아이콘을 명함에 표시하는 부분
                Box(
                    modifier = Modifier
                        .fillMaxHeight()
                        .padding(end = 8.dp)  // 오른쪽 끝으로 패딩 추가
                        .align(Alignment.CenterVertically)  // 세로 중앙 정렬
                        .pointerInput(Unit) {
                            // 사용자가 터치한 위치로 아이콘 이동
                            detectTapGestures { tapOffset ->
                                iconPosition = tapOffset
                            }
                        }
                ) {
                    Text(
                        text = selectedIcon,  // 명함에 표시될 아이콘
                        fontSize = 48.sp,
                        modifier = Modifier.offset(
                            x = animatedPosition.x.dp,
                            y = animatedPosition.y.dp
                        ) // 애니메이션된 위치로 이동
                    )
                }

                // QR 코드 이미지 고정 부분
                Box(
                    modifier = Modifier
                        .fillMaxHeight()
                        .padding(start = 8.dp)  // 왼쪽 정렬을 위한 패딩
                        .align(Alignment.CenterVertically)  // 세로 중앙 정렬
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.su_ic_qr),  // QR 코드 이미지 리소스 사용
                        contentDescription = "QR Code",
                        modifier = Modifier.size(64.dp) // 아이콘 크기 설정
                    )
                }
            }
        }

        // 버튼 영역
        Spacer(modifier = Modifier.height(50.dp))  // 명함과 버튼 사이에 여백 추가
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            shape = RoundedCornerShape(12.dp),
            elevation = CardDefaults.cardElevation(8.dp), // 카드에 그림자 효과 추가
            colors = CardDefaults.cardColors(containerColor = Color.White)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    // 탭 버튼들
                    TabButton(
                        "글자",
                        painterResource(id = R.drawable.su_ic_text),
                        selectedTab
                    ) { selectedTab = "글자" }
                    TabButton(
                        "배경",
                        painterResource(id = R.drawable.su_ic_background),
                        selectedTab
                    ) { selectedTab = "배경" }
                    TabButton(
                        "아이콘",
                        painterResource(id = R.drawable.su_ic_icon),
                        selectedTab
                    ) { selectedTab = "아이콘" }
                }

                Spacer(modifier = Modifier.height(16.dp))

                // 아이콘 탭 UI를 화면에 표시
                if (selectedTab == "아이콘") {
                    IconCustomizationScreen { newIcon -> selectedIcon = newIcon } // 아이콘 선택 후 업데이트
                }
            }
        }
    }
}

@Composable
fun TabButton(label: String, icon: Painter, selectedTab: String, onClick: () -> Unit) {
    val isSelected = label == selectedTab // 현재 선택된 탭인지 확인
    Button(
        onClick = onClick,  // 클릭 시 탭 선택 변경
        modifier = Modifier
            .padding(4.dp)  // 버튼 간의 간격
            .size(120.dp),  // 버튼 크기 설정
        shape = RoundedCornerShape(16.dp), // 둥근 모서리 처리
        colors = ButtonDefaults.buttonColors(
            containerColor = if (isSelected) Color(0xFF95C88A) else Color.White // 선택된 버튼 색상
        ),
        elevation = ButtonDefaults.buttonElevation(4.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Icon(
                painter = icon,
                contentDescription = label,
                tint = if (isSelected) Color.White else Color.Gray,  // 선택된 버튼은 흰색
                modifier = Modifier.size(50.dp) // 아이콘 크기
            )
            Spacer(modifier = Modifier.height(6.dp)) // 텍스트와 아이콘 간의 간격
            Text(
                text = label,
                color = if (isSelected) Color.White else Color.Black, // 선택된 버튼 텍스트 색상
                fontSize = 14.sp // 텍스트 크기
            )
        }
    }
}

@Composable
fun IconCustomizationScreen(onIconSelected: (String) -> Unit) {
    var emojiInput by remember { mutableStateOf("") } // 이모지 입력 상태를 관리

    Column(
        modifier = Modifier.fillMaxWidth() // 전체 너비를 채우도록 수정
    ) {
        Text("아이콘을 선택하세요", fontSize = 16.sp)  // 안내 텍스트
        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = emojiInput,
            onValueChange = { emojiInput = it },  // 입력된 이모지 값을 상태에 반영
            placeholder = { Text("아이콘을 입력하세요") },
            modifier = Modifier.fillMaxWidth() // 입력 필드 너비 전체 사용
        )

        Spacer(modifier = Modifier.height(8.dp))

        // 오른쪽 끝에 버튼 정렬
        Button(
            onClick = {
                if (emojiInput.isNotBlank()) {
                    onIconSelected(emojiInput) // 이모지 적용
                }
            },
            modifier = Modifier
                .padding(8.dp)
                .align(Alignment.End),  // 오른쪽 끝으로 버튼 정렬
            shape = RoundedCornerShape(8.dp), // 버튼 모서리 둥글게 처리
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF7FBE85)),
            elevation = ButtonDefaults.buttonElevation(4.dp) // 버튼에 그림자 효과 추가
        ) {
            Text("적용")  // 버튼 텍스트
        }
    }
}
