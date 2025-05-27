package com.hongul.fliq.ui.minjiiiiii

import android.graphics.BitmapFactory
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hongul.fliq.R
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.draw.clip

@Composable
fun IconScreen() {
    val context = LocalContext.current
    var selectedTab by remember { mutableStateOf("배경") } // 기본은 '배경' 탭 선택
    var selectedImageUri by remember { mutableStateOf<Uri?>(null) }

    val imagePickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        selectedImageUri = uri
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // 명함 카드
        Spacer(modifier = Modifier.height(100.dp))
        Box(
            modifier = Modifier
                .width(350.dp)      // 가로 키우기
                .height(200.dp)     //  세로 키우기
                .padding(horizontal = 20.dp)

        ) {
            if (selectedImageUri != null) {
                val inputStream = context.contentResolver.openInputStream(selectedImageUri!!)
                val bitmap = BitmapFactory.decodeStream(inputStream)
                inputStream?.close()
                bitmap?.let {
                    Image(
                        bitmap = it.asImageBitmap(),
                        contentDescription = "Selected Background",
                        modifier = Modifier.matchParentSize()
                    )
                }
            } else {
                // 배경 이미지가 없을 때 기본 이미지(cardpre.png)로 표시

                Image(
                    painter = painterResource(id = R.drawable.mj_ic_cardpre),
                    contentDescription = "Default Card Background",
                    modifier = Modifier.matchParentSize(),
                    contentScale = ContentScale.Crop
                )

            }


            // 명함 내용
            Card(
                modifier = Modifier.matchParentSize(),
                colors = CardDefaults.cardColors(containerColor = Color.Transparent),
                shape = RoundedCornerShape(12.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start
                ) {
                    Column(
                        modifier = Modifier.weight(1f),
                        verticalArrangement = Arrangement.Center,
                    ) {
                        Text("홍얼홍얼", fontSize = 16.sp)
                        Text("+82)10.0000.0000", fontSize = 16.sp)
                        Text("xxx@stu.kmu.ac.kr", fontSize = 16.sp)
                        Text("Wishlist _ can't be blue", fontSize = 16.sp)
                    }
                    Box(
                        modifier = Modifier
                            .fillMaxHeight()
                            .padding(end = 8.dp)
                            .align(Alignment.Bottom)
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.mj_ic_qr),
                            contentDescription = "QR Code",
                            modifier = Modifier.size(64.dp)
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(50.dp))

        // 하단 탭 카드
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            shape = RoundedCornerShape(12.dp),
            elevation = CardDefaults.cardElevation(8.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    TabButton("글자", painterResource(id = R.drawable.mj_ic_text), selectedTab) {
                        selectedTab = "글자"
                    }
                    TabButton("배경", painterResource(id = R.drawable.mj_ic_background), selectedTab) {
                        selectedTab = "배경"
                    }
                    TabButton("아이콘", painterResource(id = R.drawable.mj_ic_icon), selectedTab) {
                        selectedTab = "아이콘"
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                when (selectedTab) {
                    "아이콘" -> {
                        Text("아이콘 탭이 선택됨", fontSize = 16.sp)
                    }

                    "글자" -> {
                        Text("글자 탭이 선택됨", fontSize = 16.sp)
                    }

                    "배경" -> {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 24.dp)
                        ) {
                            Text(
                                text = "이미지",
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Medium,
                                modifier = Modifier.padding(start = 8.dp, bottom = 12.dp)
                            )

                            // 업로드 박스
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(180.dp)
                                    .clip(RoundedCornerShape(16.dp))
                                    .border(
                                        width = 1.dp,
                                        color = Color.LightGray,
                                        shape = RoundedCornerShape(16.dp)
                                    )
                                    .clickable {
                                        imagePickerLauncher.launch("image/*")
                                    },
                                contentAlignment = Alignment.Center
                            ) {
                                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                    Icon(
                                        painter = painterResource(id = R.drawable.mj_ic_uploadbar),
                                        contentDescription = "Upload Background",
                                        modifier = Modifier.size(40.dp),
                                        tint = Color.Gray
                                    )
                                    Spacer(modifier = Modifier.height(8.dp))
                                    Text(
                                        text = "배경 이미지를 업로드 해주세요.",
                                        color = Color.Gray,
                                        fontSize = 13.sp
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

@Composable
fun TabButton(label: String, icon: Painter, selectedTab: String, onClick: () -> Unit) {
    val isSelected = label == selectedTab
    Button(
        onClick = onClick,
        modifier = Modifier
            .padding(4.dp)
            .size(120.dp),
        shape = RoundedCornerShape(16.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = if (isSelected) Color(0xFF95C88A) else Color.White
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
                tint = if (isSelected) Color.White else Color.Gray,
                modifier = Modifier.size(50.dp)
            )
            Spacer(modifier = Modifier.height(6.dp))
            Text(
                text = label,
                color = if (isSelected) Color.White else Color.Black,
                fontSize = 14.sp
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun IconScreenPreview() {
    MaterialTheme {
        Surface(modifier = Modifier.fillMaxSize()) {
            IconScreen()
        }
    }
}
