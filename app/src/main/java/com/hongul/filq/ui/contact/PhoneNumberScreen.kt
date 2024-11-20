package com.hongul.filq.ui.contact

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.Close
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.Dp
import com.hongul.filq.R
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PhoneNumberScreen(modifier: Modifier = Modifier) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "명함", fontSize = 20.sp, fontWeight = FontWeight.Bold,
                        textDecoration = TextDecoration.Underline
                    )
                },
                navigationIcon = {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.KeyboardArrowLeft,
                        contentDescription = null
                    )
                }
            )
        }
    ) { innerPadding ->
        Surface(modifier = Modifier.padding(innerPadding)) {
            val bottomSheetState = rememberBottomSheetScaffoldState(
                bottomSheetState = rememberModalBottomSheetState()
            )
            val scope = rememberCoroutineScope()
            var selectedCategory by remember { mutableStateOf(0) }
            var categories by remember { mutableStateOf(listOf("개인", "업무")) }
            var showDeleteDialog by remember { mutableStateOf<Pair<Boolean, Int>>(false to -1) }
            var showContactPopup by remember { mutableStateOf(false) } // 팝업 상태 추가

            // 정렬 상태
            var sortOrder by remember { mutableStateOf("이름순") }

            // 명함 리스트
            var personalContacts by remember {
                mutableStateOf(
                    listOf(
                        Triple("홍추핑구", "+82 010 1234 5670", "zza@stu.kmu.ac.kr"),
                        Triple("홍추핑", "+82 010 1234 5678", "zzz@stu.kmu.ac.kr")
                    )
                )
            }

            BottomSheetScaffold(
                scaffoldState = bottomSheetState,
                sheetContent = {
                    Category(
                        onAddCategory = { newCategory ->
                            if (newCategory.isNotBlank()) {
                                categories = categories + newCategory
                                scope.launch { bottomSheetState.bottomSheetState.hide() }
                            }
                        }
                    )
                },
                sheetPeekHeight = 0.dp
            ) {
                Column(
                    modifier = Modifier.fillMaxSize()
                ) {
                    // 카테고리 선택
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 20.dp, horizontal = 16.dp),
                        horizontalArrangement = Arrangement.Start
                    ) {
                        categories.forEachIndexed { index, category ->
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Button(
                                    onClick = { selectedCategory = index },
                                    colors = ButtonDefaults.buttonColors(
                                        if (selectedCategory == index) Color(0xFF125422) else Color.Transparent
                                    )
                                ) {
                                    Text(
                                        category,
                                        color = if (selectedCategory == index) Color.White else Color.Black
                                    )
                                }
                                Spacer(modifier = Modifier.width(4.dp))

                                // 삭제 버튼
                                Icon(
                                    imageVector = Icons.Default.Close,
                                    contentDescription = "삭제",
                                    modifier = Modifier
                                        .size(16.dp)
                                        .clickable {
                                            showDeleteDialog = true to index
                                        },
                                    tint = Color.Red
                                )
                            }
                            Spacer(modifier = Modifier.width(10.dp))
                        }

                        // "+" 버튼
                        if (categories.size < 3) {
                            Button(
                                colors = ButtonDefaults.buttonColors(Color.Transparent),
                                onClick = { scope.launch { bottomSheetState.bottomSheetState.expand() } }
                            ) {
                                Text("+", fontSize = 25.sp, color = Color.Black)
                            }
                        }
                    }

                    // 정렬 UI
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp),
                        horizontalArrangement = Arrangement.End,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.clickable {
                                if (sortOrder == "이름순") {
                                    sortOrder = "최근등록순"
                                    personalContacts = personalContacts.reversed() // 최근등록순
                                } else {
                                    sortOrder = "이름순"
                                    personalContacts = personalContacts.sortedBy { it.first } // 이름순
                                }
                            }
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.arrow_down_up),
                                contentDescription = "정렬 아이콘",
                                tint = Color(0xFF125422),
                                modifier = Modifier.size(16.dp)
                            )
                            Spacer(modifier = Modifier.width(4.dp))
                            Text(
                                sortOrder,
                                fontSize = 14.sp,
                                color = Color(0xFF125422),
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }

                    // 선택된 카테고리에 따라 내용 변경
                    if (selectedCategory == 0) {
                        // 개인 카테고리
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(16.dp),
                            verticalArrangement = Arrangement.Top
                        ) {
                            personalContacts.forEach { contact ->
                                Card(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(vertical = 8.dp),
                                    shape = RoundedCornerShape(30.dp),
                                    colors = CardDefaults.cardColors(containerColor = Color(0xFFD8F3DC))
                                ) {
                                    Row(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(16.dp),
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        Image(
                                            painter = painterResource(id = R.drawable.daemori_image),
                                            contentDescription = null,
                                            modifier = Modifier
                                                .size(40.dp)
                                                .background(Color.LightGray, shape = CircleShape)
                                        )
                                        Spacer(modifier = Modifier.width(16.dp))

                                        Column {
                                            Text(contact.first, fontWeight = FontWeight.Bold, fontSize = 16.sp,
                                                color = Color(0xFF125422))
                                            Divider(
                                                color = Color(0xFF125422),
                                                thickness = 1.dp,
                                                modifier = Modifier.fillMaxWidth(0.9f)
                                            )
                                            Spacer(modifier = Modifier.height(4.dp))
                                            Text(contact.second, fontSize = 14.sp, color = Color(0xFF125422))
                                            Spacer(modifier = Modifier.height(4.dp))
                                            Text(contact.third, fontSize = 14.sp, color = Color(0xFF125422))
                                        }
                                    }
                                }
                            }
                        }
                    } else {
                        // 업무 및 새로 추가된 카테고리
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(16.dp),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.daemori_image),
                                contentDescription = null,
                                modifier = Modifier.size(200.dp)
                            )

                            Text(
                                "지인과 명함을 주고받아 편리하게 관리하세요.",
                                fontSize = 12.sp,
                                color = Color.Gray,
                                textAlign = TextAlign.Center,
                                modifier = Modifier.padding(16.dp)
                            )
                            Spacer(modifier = Modifier.height(16.dp))

                            Button(
                                onClick = { showContactPopup = true }, // 팝업 표시
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(50.dp),
                                shape = RoundedCornerShape(12.dp),
                                colors = ButtonDefaults.buttonColors(Color(0xFF125422))
                            ) {
                                Text("명함 추가하기")
                            }
                        }
                    }
                }

                // ContactBottomSheet 팝업
                if (showContactPopup) {
                    ContactBottomSheet(onDismiss = { showContactPopup = false })
                }
            }

            // 삭제 팝업
            if (showDeleteDialog.first) {
                AlertDialog(
                    onDismissRequest = { showDeleteDialog = false to -1 },
                    title = { Text("카테고리 삭제") },
                    text = {
                        Text("카테고리에 있던 모든 명함이 함께 사라집니다. 삭제하시겠습니까?")
                    },
                    confirmButton = {
                        TextButton(onClick = {
                            categories = categories.toMutableList().apply {
                                removeAt(showDeleteDialog.second)
                            }
                            showDeleteDialog = false to -1
                        }) {
                            Text("삭제", color = Color.Red)
                        }
                    },
                    dismissButton = {
                        TextButton(onClick = {
                            showDeleteDialog = false to -1
                        }) {
                            Text("취소")
                        }
                    }
                )
            }
        }
    }
}




@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Category(onAddCategory: (String) -> Unit) {
    var text by remember { mutableStateOf("") }

    Spacer(modifier = Modifier.width(40.dp))
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextField(
            value = text,
            onValueChange = { text = it },
            placeholder = { Text("추가할 카테고리를 입력하세요") },
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.LightGray, shape = RoundedCornerShape(8.dp)), // 밝은 회색 배경
            shape = RoundedCornerShape(8.dp), // 둥근 모서리
            colors = TextFieldDefaults.textFieldColors(
                containerColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent
            )
        )

        Spacer(modifier = Modifier.height(10.dp))

        Button(
            onClick = {
                onAddCategory(text)
                text = "" // 입력 초기화
            },
            modifier = Modifier.align(Alignment.End),
            colors = ButtonDefaults.buttonColors(Color.Transparent)
        ) {
            Text("추가", color = Color(0xFF125422), fontWeight = FontWeight.Bold)
        }
    }
}

@Composable
fun ContactBottomSheet(modifier: Modifier = Modifier, onDismiss: () -> Unit) {
    Dialog(onDismissRequest = { onDismiss()}) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ){
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 30.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(16.dp))

                Text("명함을 저장하고 인맥을\n관리하세요",
                    style = MaterialTheme.typography.bodyLarge,
                    textAlign = TextAlign.Center, // 중앙 정렬
                    fontWeight = FontWeight.SemiBold
                )

                Spacer(modifier = Modifier.height(30.dp))

                CircleIconButton(
                    icon = ImageVector.vectorResource(R.drawable.ic_cameraalt),
                    size = 48.dp,
                    onClick = { /* 카메라 촬영 클릭 처리 */ }
                )
                Spacer(modifier = Modifier.height(10.dp))

                Text("명함 촬영", fontSize = 10.sp)

                Spacer(modifier = Modifier.height(20.dp))

                Divider( // 선 추가
                    color = Color(0xFF125422),
                    thickness = 1.5.dp,
                    modifier = Modifier.fillMaxWidth(0.8f) // 선의 길이를 부모 너비의 80%로 설정
                )
                Spacer(modifier = Modifier.height(20.dp))

                Text(
                    "명함과 대비되는 깔끔한 배경에서 촬영해 주세요.",
                    fontSize = 12.sp, color = Color.Gray
                )
            }
        }

    }
}


@Composable
fun CircleIconButton(icon: ImageVector, size: Dp, onClick: () -> Unit) {
    Surface(
        shape = CircleShape,
        modifier = Modifier.size(size).clickable(onClick = onClick)
    ) {
        Icon(icon, contentDescription = null,
            modifier = Modifier.fillMaxSize().background(Color(0xFF7FBE85)).padding(10.dp),
            tint = Color.White)
    }
}

@Preview(showBackground = true)
@Composable
fun Category() {
    Category(onAddCategory = {})
}

@Preview(showBackground = true)
@Composable
fun ContactBottomSheetPreview() {
    ContactBottomSheet(onDismiss = {})
}

@Preview(showBackground = true)
@Composable
fun PhoneNumberPreview() {
    PhoneNumberScreen(Modifier.fillMaxSize())
}