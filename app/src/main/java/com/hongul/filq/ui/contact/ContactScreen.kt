package com.hongul.filq.ui.contact

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.Dp
import com.hongul.filq.R
import kotlinx.coroutines.launch


// 명함 데이터 구조 수정 (상태 메시지 추가)
data class ContactCard(
    val name: String,
    val phone: String,
    val email: String,
    val statusMessage: String // 상태 메시지
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ContactScreen() {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "명함",
                        fontSize = 20.sp,
                        modifier = Modifier.padding(horizontal = 10.dp)
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
            var categories by remember { mutableStateOf(listOf("전체", "즐겨찾기", "개인", "업무")) }
            var showDeleteDialog by remember { mutableStateOf<Pair<Boolean, Int>>(false to -1) }
            var showContactPopup by remember { mutableStateOf(false) }
            var favoriteContacts by remember { mutableStateOf(setOf<String>()) }
            var sortOrder by remember { mutableStateOf("이름순") }

            // 명함 리스트
            var personalContacts by remember {
                mutableStateOf(
                    listOf(
                        ContactCard(
                            name = "홍추핑구",
                            phone = "+82 010 1234 5670",
                            email = "zza@stu.kmu.ac.kr",
                            statusMessage = "이번주는 바빠서 연락이 힘들 예정이니까 아무도 연락하지 마세요."
                        ),
                        ContactCard(
                            name = "홍추핑",
                            phone = "+82 010 1234 5678",
                            email = "zzz@stu.kmu.ac.kr",
                            statusMessage = "회의 중입니다. 나중에 연락드릴게요."
                        )
                    )
                )
            }
            var filteredContacts by remember { mutableStateOf(personalContacts) }

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
                Column(modifier = Modifier.fillMaxSize()) {
                    // 카테고리 선택
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 20.dp, horizontal = 16.dp)
                            .horizontalScroll(rememberScrollState()),
                        horizontalArrangement = Arrangement.Start
                    ) {
                        categories.forEachIndexed { index, category ->
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Button(
                                    onClick = {
                                        selectedCategory = index
                                        filteredContacts = when (index) {
                                            0 -> personalContacts // 전체 카테고리
                                            1 -> personalContacts.filter { it.name in favoriteContacts } // 즐겨찾기
                                            2 -> personalContacts // 개인 카테고리
                                            3 -> emptyList() // 업무 카테고리
                                            else -> emptyList()
                                        }
                                    },
                                    colors = ButtonDefaults.buttonColors(
                                        if (selectedCategory == index) Color(0xFF125422) else Color.Transparent
                                    )
                                ) {
                                    Text(
                                        category,
                                        color = if (selectedCategory == index) Color.White else Color.Black,
                                        fontSize = 18.sp
                                    )
                                }
                                Spacer(modifier = Modifier.width(4.dp))

                                if (index >= 4) {
                                    Icon(
                                        imageVector = Icons.Default.Close,
                                        contentDescription = "삭제",
                                        modifier = Modifier
                                            .size(16.dp)
                                            .clickable { showDeleteDialog = true to index },
                                        tint = Color.Red
                                    )
                                }
                            }
                            Spacer(modifier = Modifier.width(10.dp))
                        }

                        if (categories.size < 7) {
                            Button(
                                colors = ButtonDefaults.buttonColors(Color.Transparent),
                                onClick = { scope.launch { bottomSheetState.bottomSheetState.expand() } }
                            ) {
                                Text("+", fontSize = 25.sp, color = Color.Black)
                            }
                        }
                    }

                    // 삭제 팝업
                    if (showDeleteDialog.first) {
                        DeleteCategoryDialog(
                            categoryName = categories[showDeleteDialog.second],
                            onCancel = { showDeleteDialog = false to -1 },
                            onDelete = {
                                categories = categories.toMutableList().apply { removeAt(showDeleteDialog.second) }
                                showDeleteDialog = false to -1
                            }
                        )
                    }

                    // 검색창 추가
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp, vertical = 4.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        var searchType by remember { mutableStateOf("이름") }
                        var isDropdownExpanded by remember { mutableStateOf(false) }
                        var searchQuery by remember { mutableStateOf("") }

                        Box(
                            modifier = Modifier
                                .height(40.dp)
                                .wrapContentSize(Alignment.CenterStart)
                                .clickable { isDropdownExpanded = !isDropdownExpanded }
                                .padding(horizontal = 8.dp)
                        ) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Text(
                                    text = searchType,
                                    fontSize = 14.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = Color(0xFF125422)
                                )
                                Spacer(modifier = Modifier.width(4.dp))
                                Icon(
                                    painter = painterResource(id = R.drawable.ic_down_arrow),
                                    contentDescription = "드롭다운 아이콘",
                                    modifier = Modifier.size(16.dp),
                                    tint = Color(0xFF125422)
                                )
                            }
                            DropdownMenu(
                                expanded = isDropdownExpanded,
                                onDismissRequest = { isDropdownExpanded = false }
                            ) {
                                DropdownMenuItem(
                                    onClick = {
                                        searchType = "이름"
                                        isDropdownExpanded = false
                                    },
                                    text = { Text("이름") }
                                )
                                DropdownMenuItem(
                                    onClick = {
                                        searchType = "회사명"
                                        isDropdownExpanded = false
                                    },
                                    text = { Text("회사명") }
                                )
                                DropdownMenuItem(
                                    onClick = {
                                        searchType = "태그"
                                        isDropdownExpanded = false
                                    },
                                    text = { Text("태그") }
                                )
                            }
                        }

                        Spacer(modifier = Modifier.width(8.dp))

                        TextField(
                            value = searchQuery,
                            onValueChange = { searchQuery = it },
                            placeholder = {
                                Text(
                                    text = "검색어 입력",
                                    fontSize = 13.sp,
                                    color = Color.Gray
                                )
                            },
                            modifier = Modifier
                                .weight(1f)
                                .height(56.dp)
                                .background(Color.Transparent),
                            colors = TextFieldDefaults.textFieldColors(
                                containerColor = Color.Transparent,
                                focusedIndicatorColor = Color(0xFF125422),
                                unfocusedIndicatorColor = Color(0xFF125422),
                                disabledIndicatorColor = Color.Transparent
                            ),
                            textStyle = LocalTextStyle.current.copy(
                                fontSize = 13.sp,
                                color = Color.Black
                            ),
                            shape = RoundedCornerShape(0.dp)
                        )

                        Spacer(modifier = Modifier.width(8.dp))

                        Button(
                            onClick = {
                                filteredContacts = when (searchType) {
                                    "이름" -> personalContacts.filter {
                                        it.name.contains(
                                            searchQuery,
                                            ignoreCase = true
                                        )
                                    }

                                    "회사명" -> personalContacts.filter {
                                        it.phone.contains(
                                            searchQuery,
                                            ignoreCase = true
                                        )
                                    }

                                    "태그" -> personalContacts.filter {
                                        it.email.contains(
                                            searchQuery,
                                            ignoreCase = true
                                        )
                                    }

                                    else -> personalContacts
                                }
                            },
                            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF125422)),
                            modifier = Modifier
                                .height(40.dp)
                                .padding(horizontal = 8.dp),
                            shape = RoundedCornerShape(8.dp)
                        ) {
                            Text("검색", color = Color.White, fontSize = 13.sp)
                        }
                    }
                    Spacer(modifier = Modifier.height(8.dp))

                    // 정렬 UI
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 25.dp),
                        horizontalArrangement = Arrangement.End,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.clickable {
                                if (sortOrder == "이름순") {
                                    sortOrder = "최근등록순"
                                    personalContacts = personalContacts.reversed()
                                    filteredContacts = filteredContacts.reversed()
                                } else {
                                    sortOrder = "이름순"
                                    personalContacts = personalContacts.sortedBy { it.name }
                                    filteredContacts = filteredContacts.sortedBy { it.name }
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

                    // 필터링된 명함 리스트
                    if (filteredContacts.isEmpty() && selectedCategory != 2) {
                        if (selectedCategory == 1) {
                            Box(
                                modifier = Modifier.fillMaxSize(),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = "즐겨찾기한 명함이 없습니다.",
                                    fontSize = 16.sp,
                                    color = Color.Gray,
                                    textAlign = TextAlign.Center
                                )
                            }
                        } else {
                            Box(
                                modifier = Modifier.fillMaxSize(),
                                contentAlignment = Alignment.Center
                            ) {
                                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                    Image(
                                        painter = painterResource(id = R.drawable.daemori_image),
                                        contentDescription = null,
                                        modifier = Modifier.size(250.dp)
                                    )
                                    Spacer(modifier = Modifier.height(16.dp))
                                    Text(
                                        text = "지인과 명함을 주고받아\n편리하게 관리하세요.",
                                        textAlign = TextAlign.Center,
                                        fontSize = 14.sp,
                                        color = Color.Gray
                                    )
                                    Spacer(modifier = Modifier.height(50.dp))
                                    Button(
                                        onClick = { showContactPopup = true },
                                        colors = ButtonDefaults.buttonColors(
                                            containerColor = Color(
                                                0xFF125422
                                            )
                                        ),
                                        shape = RoundedCornerShape(8.dp),
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(horizontal = 32.dp)
                                            .height(53.dp)
                                    ) {
                                        Text(
                                            "명함 추가하기",
                                            color = Color.White,
                                            fontSize = 16.sp,
                                            textAlign = TextAlign.Center
                                        )
                                    }
                                }
                            }
                        }
                    } else {
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(16.dp)
                                .verticalScroll(rememberScrollState()),
                            verticalArrangement = Arrangement.Top
                        ) {
                            filteredContacts.forEach { contact ->
                                Card(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(vertical = 8.dp),
                                    shape = RoundedCornerShape(30.dp),
                                    colors = CardDefaults.cardColors(
                                        containerColor = Color(
                                            0xFFD8F3DC
                                        )
                                    )
                                ) {
                                    Row(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(16.dp),
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        Box(
                                            contentAlignment = Alignment.Center,
                                            modifier = Modifier
                                                .size(70.dp)
                                                .background(Color(0xFF7FBE85), shape = CircleShape)
                                        ) {
                                            Image(
                                                painter = painterResource(id = R.drawable.stickers1),
                                                contentDescription = null,
                                                modifier = Modifier
                                                    .size(55.dp)
                                                    .offset(x = 2.dp, y = 2.dp),
                                                contentScale = ContentScale.Fit
                                            )
                                        }
                                        Spacer(modifier = Modifier.width(16.dp))
                                        Column {
                                            Row(
                                                verticalAlignment = Alignment.CenterVertically,
                                                modifier = Modifier.fillMaxWidth()
                                            ) {
                                                Text(
                                                    contact.name,
                                                    fontWeight = FontWeight.Bold,
                                                    fontSize = 16.sp,
                                                    color = Color(0xFF125422)
                                                )
                                                Spacer(modifier = Modifier.width(8.dp))
                                                Divider(
                                                    color = Color(0xFF125422),
                                                    modifier = Modifier
                                                        .height(18.dp)
                                                        .width(1.dp)
                                                )
                                                Spacer(modifier = Modifier.width(8.dp))
                                                Text(
                                                    text = if (contact.statusMessage.length > 20) {
                                                        "${contact.statusMessage.take(20)}..."
                                                    } else {
                                                        contact.statusMessage
                                                    },
                                                    fontSize = 12.sp,
                                                    color = Color.Gray
                                                )
                                                Spacer(modifier = Modifier.weight(1f))
                                                Icon(
                                                    painter = if (contact.name in favoriteContacts) {
                                                        painterResource(id = R.drawable.star_filled)
                                                    } else {
                                                        painterResource(id = R.drawable.star_outline)
                                                    },
                                                    contentDescription = "즐겨찾기",
                                                    modifier = Modifier
                                                        .size(24.dp)
                                                        .clickable {
                                                            if (contact.name in favoriteContacts) {
                                                                favoriteContacts =
                                                                    favoriteContacts - contact.name
                                                            } else {
                                                                favoriteContacts =
                                                                    favoriteContacts + contact.name
                                                            }
                                                        },
                                                    tint = Color(0xFF125422)
                                                )
                                            }
                                            Divider(
                                                color = Color(0xFF125422),
                                                thickness = 1.dp,
                                                modifier = Modifier.fillMaxWidth(0.9f)
                                            )
                                            Spacer(modifier = Modifier.height(4.dp))
                                            Text(
                                                contact.phone,
                                                fontSize = 14.sp,
                                                color = Color(0xFF125422)
                                            )
                                            Spacer(modifier = Modifier.height(4.dp))
                                            Text(
                                                contact.email,
                                                fontSize = 14.sp,
                                                color = Color(0xFF125422)
                                            )
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}


@Composable
fun DeleteCategoryDialog(
    categoryName: String,
    onCancel: () -> Unit,
    onDelete: () -> Unit
) {
    Dialog(onDismissRequest = { onCancel() }) {
        Card(
            shape = RoundedCornerShape(12.dp),
            modifier = Modifier.padding(16.dp)
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "\"$categoryName\" 카테고리를\n삭제하시겠습니까?",
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(vertical = 8.dp)
                )
                Text(
                    text = "카테고리에 있는 명함은 모두 삭제됩니다.",
                    fontSize = 14.sp,
                    color = Color.Gray,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(vertical = 8.dp)
                )
                Spacer(modifier = Modifier.height(16.dp))
                Row(
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Button(
                        onClick = onCancel,
                        colors = ButtonDefaults.buttonColors(containerColor = Color.LightGray),
                        shape = RoundedCornerShape(12.dp),
                        modifier = Modifier
                            .height(40.dp)
                            .weight(1f)
                    ) {
                        Text("취소", color = Color.Black)
                    }

                    Spacer(modifier = Modifier.width(8.dp))

                    Button(
                        onClick = onDelete,
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF125422)),
                        shape = RoundedCornerShape(12.dp),
                        modifier = Modifier
                            .height(40.dp)
                            .weight(1f)
                    ) {
                        Text("삭제", color = Color.White)
                    }
                }
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
fun ContactScreenPreview() {
    ContactScreen()
}