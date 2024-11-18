package com.hongul.filq.ui.calendar

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hongul.filq.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScheduleEditScreen(
    schedule: Schedule,
    contacts: List<Contact>,
    defaultColor: Color, // 기본 색상 추가
    onClose: () -> Unit,
    onDelete: () -> Unit,
    onColorChange: (Color) -> Unit // 색상 변경 콜백 추가
) {
    var title by remember { mutableStateOf(schedule.title) }
    var color by remember { mutableStateOf(schedule.color) }
    val selectedParticipants = remember { mutableStateListOf<Contact>().apply { addAll(schedule.participants) } }
    var showDeleteConfirmation by remember { mutableStateOf(false) }
    var showColorPickerDialog by remember { mutableStateOf(false) } // 색상 선택 팝업 상태 추가

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("일정 수정", fontSize = 18.sp) },
                navigationIcon = {
                    IconButton(onClick = onClose) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = null
                        )
                    }
                },
                actions = {
                    IconButton(
                        onClick = { showDeleteConfirmation = true }
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.trash),
                            contentDescription = "삭제",
                            tint = Color.Gray
                        )
                    }
                },
                colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = Color.White)
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(Color.White)
                .padding(16.dp)
        ) {
            // 일정 이름과 색상
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Box(
                    modifier = Modifier
                        .size(40.dp)
                        .background(color, shape = CircleShape)
                        .clickable { showColorPickerDialog = true } // 색상 선택 팝업 열기
                )
                Spacer(modifier = Modifier.width(16.dp))
                TextField(
                    value = title,
                    onValueChange = { title = it },
                    label = { Text("일정 이름") },
                    modifier = Modifier.weight(1f),
                    colors = TextFieldDefaults.textFieldColors(containerColor = Color.Transparent)
                )
            }
            Spacer(modifier = Modifier.height(16.dp))

            // 참여자 선택
            Text(text = "참여자", fontWeight = FontWeight.Bold, fontSize = 16.sp)
            contacts.forEach { contact ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            if (selectedParticipants.contains(contact)) {
                                selectedParticipants.remove(contact)
                            } else {
                                selectedParticipants.add(contact)
                            }
                        }
                        .padding(vertical = 8.dp)
                ) {
                    Checkbox(
                        checked = selectedParticipants.contains(contact),
                        onCheckedChange = {
                            if (it) {
                                selectedParticipants.add(contact)
                            } else {
                                selectedParticipants.remove(contact)
                            }
                        },
                        colors = CheckboxDefaults.colors(
                            checkedColor = Color(0xFF1B5E20), // 녹색 체크박스
                            uncheckedColor = Color.Gray
                        )
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(text = "${contact.name} (${contact.email})")
                }
            }

            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = {
                    schedule.title = title
                    schedule.color = color
                    schedule.participants.clear()
                    schedule.participants.addAll(selectedParticipants)
                    onClose()
                },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF1B5E20)) // 녹색 저장 버튼
            ) {
                Text("저장", color = Color.White)
            }
        }
    }

    // 삭제 확인 팝업
    if (showDeleteConfirmation) {
        AlertDialog(
            onDismissRequest = { showDeleteConfirmation = false },
            title = { Text("일정을 삭제하시겠습니까?") },
            text = { Text("확인을 누르면 일정이 완전히 삭제됩니다.") },
            confirmButton = {
                TextButton(
                    onClick = {
                        onDelete()
                        showDeleteConfirmation = false
                    },
                    colors = ButtonDefaults.textButtonColors(
                        containerColor = Color.Red, // 빨간색 확인 버튼
                        contentColor = Color.White
                    ),
                    modifier = Modifier.height(40.dp)
                ) {
                    Text("확인")
                }
            },
            dismissButton = {
                TextButton(
                    onClick = { showDeleteConfirmation = false },
                    colors = ButtonDefaults.textButtonColors(
                        containerColor = Color.Transparent,
                        contentColor = Color.Black
                    )
                ) {
                    Text("취소")
                }
            },
            containerColor = Color.White
        )
    }

    // 색상 선택 팝업
    if (showColorPickerDialog) {
        ColorPickerDialog(
            onColorSelected = { selectedColor ->
                color = selectedColor
                showColorPickerDialog = false
            },
            onDismiss = { showColorPickerDialog = false }
        )
    }
}

@Preview(showBackground = true, name = "Schedule Edit Preview")
@Composable
fun ScheduleEditScreenPreview() {
    ScheduleEditScreen(
        schedule = Schedule(
            id = "1",
            title = "팀 미팅",
            color = Color.Blue.copy(alpha = 0.6f),
            participants = mutableListOf(
                Contact(id = 1, name = "홍추핑구", email = "pinggu@example.com"),
                Contact(id = 2, name = "홍추핑", email = "ping@example.com")
            )
        ),
        contacts = listOf(
            Contact(id = 1, name = "홍추핑구", email = "pinggu@example.com"),
            Contact(id = 2, name = "홍추핑", email = "ping@example.com"),
            Contact(id = 3, name = "홍길동", email = "gil@example.com")
        ),
        defaultColor = Color.Red.copy(alpha = 0.6f),
        onClose = {},
        onDelete = {},
        onColorChange = {}
    )
}