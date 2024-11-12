package com.hongul.filq.ui.theme.Calendar

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.time.LocalDate
import java.time.YearMonth
import java.time.format.TextStyle
import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CalendarScreen() {
    var currentMonth by remember { mutableStateOf(YearMonth.now()) }
    val today = LocalDate.now()

    Scaffold(
        topBar = {
            SmallTopAppBar(
                title = { Text("캘린더", fontSize = 18.sp) },
                navigationIcon = {
                    IconButton(onClick = {
                        currentMonth = currentMonth.minusMonths(1) // 이전 달로 이동
                    }) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = "이전 달")
                    }
                },
                actions = {
                    IconButton(onClick = {
                        currentMonth = currentMonth.plusMonths(1) // 다음 달로 이동
                    }) {
                        Icon(Icons.Filled.ArrowForward, contentDescription = "다음 달")
                    }
                },
                colors = TopAppBarDefaults.smallTopAppBarColors(
                    containerColor = Color.White,
                    titleContentColor = Color.Black,
                    navigationIconContentColor = Color.Black
                )
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(Color.White),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CalendarHeader(currentMonth)
            CalendarBody(currentMonth, today)
        }
    }
}

@Composable
fun CalendarHeader(currentMonth: YearMonth) {
    val monthDisplayName = currentMonth.month.getDisplayName(TextStyle.FULL, Locale.getDefault())
    val year = currentMonth.year

    Row(
        modifier = Modifier
            .padding(vertical = 16.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ) {
        Text(
            text = "$monthDisplayName $year",
            fontSize = 20.sp,
            fontWeight = FontWeight.SemiBold,
            color = Color(0xFF1B5E20)
        )
    }
}

@Composable
fun CalendarBody(currentMonth: YearMonth, today: LocalDate) {
    var selectedDate by remember { mutableStateOf<Int?>(null) }
    val daysInMonth = currentMonth.lengthOfMonth() // 현재 월의 일 수

    Column(
        modifier = Modifier
            .padding(16.dp)
            .background(Color.White, RoundedCornerShape(12.dp))
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // 요일 표시
        Row(
            horizontalArrangement = Arrangement.SpaceAround,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp)
        ) {
            val daysOfWeek = listOf("SUN", "MON", "TUE", "WED", "THU", "FRI", "SAT")
            daysOfWeek.forEach { day ->
                Text(text = day, fontSize = 12.sp, color = Color.Gray)
            }
        }

        // 날짜 표시
        val firstDayOfWeek = YearMonth.from(currentMonth).atDay(1).dayOfWeek.value % 7
        val dayChunks = (1..daysInMonth).toList().chunked(7)

        dayChunks.forEach { week ->
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                week.forEachIndexed { index, day ->
                    val isToday = currentMonth.year == today.year && currentMonth.month == today.month && day == today.dayOfMonth
                    Box(
                        modifier = Modifier
                            .size(40.dp)
                            .background(
                                color = if (day == selectedDate) Color(0xFF1B5E20) else Color.Transparent, // 선택된 날짜: 짙은 초록색 배경
                                shape = RoundedCornerShape(20.dp)
                            )
                            .clickable { selectedDate = day }, // 날짜 클릭 시 선택 상태 업데이트
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = day.toString(),
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Medium,
                            color = when {
                                day == selectedDate -> Color.White // 선택된 날짜: 흰색 텍스트
                                isToday -> Color(0xFF1B5E20) // 오늘 날짜: 짙은 초록색 텍스트, 배경 없음
                                else -> Color.Black // 기본 텍스트 색상
                            }
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CalendarScreenPreview() {
    CalendarScreen()
}
