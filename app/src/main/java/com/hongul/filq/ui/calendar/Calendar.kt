package com.hongul.filq.ui.calendar

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Add
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
    var selectedDate by remember { mutableStateOf<LocalDate?>(null) }
    val schedules = remember { mutableStateMapOf<LocalDate, List<String>>() }

    Scaffold(
        topBar = {
            SmallTopAppBar(
                title = { Text("캘린더", fontSize = 18.sp) },
                navigationIcon = {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.KeyboardArrowLeft,
                        contentDescription = null
                    )
                },
                colors = TopAppBarDefaults.smallTopAppBarColors(
                    containerColor = Color.White,
                    titleContentColor = Color.Black
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
            CalendarHeader(currentMonth) { isNext ->
                currentMonth = if (isNext) {
                    currentMonth.plusMonths(1)
                } else {
                    currentMonth.minusMonths(1)
                }
            }
            CalendarBody(currentMonth, today, selectedDate) { date ->
                selectedDate = date // 날짜 선택 시 selectedDate 업데이트
            }
            ScheduleList(selectedDate, schedules)
            if (selectedDate != null) { // selectedDate가 있을 때만 표시
                ScheduleInput(selectedDate) { schedule ->
                    schedules[selectedDate!!] = schedules.getOrDefault(selectedDate!!, emptyList()) + schedule
                }
            }
        }
    }
}

@Composable
fun CalendarHeader(currentMonth: YearMonth, onMonthChange: (Boolean) -> Unit) {
    val monthDisplayName = currentMonth.month.getDisplayName(TextStyle.FULL, Locale.getDefault())
    val year = currentMonth.year

    Row(
        modifier = Modifier
            .padding(vertical = 16.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(onClick = { onMonthChange(false) }) {
            Icon(Icons.Filled.ArrowBack, contentDescription = "이전 달")
        }

        Text(
            text = "$monthDisplayName $year",
            fontSize = 20.sp,
            fontWeight = FontWeight.SemiBold,
            color = Color(0xFF1B5E20)
        )

        IconButton(onClick = { onMonthChange(true) }) {
            Icon(Icons.Filled.ArrowForward, contentDescription = "다음 달")
        }
    }
}

@Composable
fun CalendarBody(currentMonth: YearMonth, today: LocalDate, selectedDate: LocalDate?, onDateSelected: (LocalDate) -> Unit) {
    val daysInMonth = currentMonth.lengthOfMonth()
    val firstDayOfMonth = currentMonth.atDay(1).dayOfWeek.value % 7 // 월의 첫 번째 요일
    val lastDayOfMonth = (firstDayOfMonth + daysInMonth - 1) % 7 // 마지막 요일 계산

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
        val days = (1..daysInMonth).toList()
        val emptyDaysBefore = List(firstDayOfMonth) { "" } // 첫 주 앞의 빈 칸
        val emptyDaysAfter = List(6 - lastDayOfMonth) { "" } // 마지막 주 뒤의 빈 칸 추가
        val dayChunks = (emptyDaysBefore + days.map { it.toString() } + emptyDaysAfter).chunked(7) // 주 단위로 나눔

        dayChunks.forEach { week ->
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                week.forEach { day ->
                    val date = day.toIntOrNull()?.let { currentMonth.atDay(it) }
                    val isToday = date == today
                    val isSelected = date == selectedDate

                    Box(
                        modifier = Modifier
                            .size(40.dp)
                            .background(
                                color = if (isSelected && selectedDate != null) Color(0xFF1B5E20) else Color.Transparent,
                                shape = RoundedCornerShape(20.dp)
                            )
                            .clickable { date?.let(onDateSelected) },
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = day,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Medium,
                            color = when {
                                isSelected -> Color.White
                                isToday -> Color(0xFF1B5E20)
                                day.isBlank() -> Color.Transparent
                                else -> Color.Black
                            }
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}

@Composable
fun ScheduleList(selectedDate: LocalDate?, schedules: Map<LocalDate, List<String>>) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        if (selectedDate != null) {
            Text(
                text = "${selectedDate.monthValue}월 ${selectedDate.dayOfMonth}일",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Gray
            )

            val daySchedules = schedules[selectedDate]
            if (daySchedules.isNullOrEmpty()) {
                Text(
                    text = "일정이 없습니다",
                    fontSize = 14.sp,
                    color = Color.Gray,
                    modifier = Modifier.padding(top = 8.dp)
                )
            } else {
                daySchedules.forEach { schedule ->
                    Text(
                        text = schedule,
                        fontSize = 14.sp,
                        color = Color.Black,
                        modifier = Modifier.padding(top = 8.dp)
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScheduleInput(selectedDate: LocalDate?, onAddSchedule: (String) -> Unit) {
    var inputText by remember { mutableStateOf("") }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 70.dp), // 입력란을 아래로 더 내리기 위해 padding 조정
        verticalAlignment = Alignment.CenterVertically
    ) {
        TextField(
            value = inputText,
            onValueChange = { inputText = it },
            placeholder = {
                Text(text = "${selectedDate?.monthValue}월 ${selectedDate?.dayOfMonth}일에 일정 추가")
            },
            modifier = Modifier
                .weight(1f)
                .background(Color(0xFFF0F0F0), RoundedCornerShape(24.dp))
                .padding(horizontal = 16.dp),
            singleLine = true,
            colors = TextFieldDefaults.textFieldColors(
                containerColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent
            )
        )
        Spacer(modifier = Modifier.width(8.dp))
        IconButton(
            onClick = {
                if (inputText.isNotBlank()) {
                    onAddSchedule(inputText)
                    inputText = ""
                }
            },
            modifier = Modifier
                .size(48.dp)
                .background(Color(0xFF1B5E20), shape = CircleShape)
        ) {
            Icon(Icons.Filled.Add, contentDescription = "일정 추가", tint = Color.White)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CalendarScreenPreview() {
    CalendarScreen()
}
