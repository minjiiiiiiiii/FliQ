@file:OptIn(ExperimentalMaterial3Api::class)

package com.hongul.filq.ui.contact

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
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

@Composable
fun MemoScreen(
    initialMemo: String,
    onSave: (String) -> Unit,
    onNavigateBack: () -> Unit
) {
    val customColor = Color(0xFF125422)
    var memo by remember { mutableStateOf(initialMemo) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_hongchuping),
                contentDescription = "Profile Image",
                modifier = Modifier.fillMaxSize()
            )
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Gray.copy(alpha = 0.5f))
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // FliQ 메모와 저장 버튼을 같은 줄에 배치
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween // 양 끝에 배치
        ) {
            Text(
                text = "FliQ 메모",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = customColor
            )
            Button(
                onClick = { onSave(memo) },
                colors = ButtonDefaults.buttonColors(containerColor = customColor),
                shape = RoundedCornerShape(12.dp)
            ) {
                Text("저장", color = Color.White)
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // 메모 입력 필드 (둥근 모서리 박스)
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .background(Color.White, shape = RoundedCornerShape(16.dp))
                .padding(16.dp)
        ) {
            TextField(
                value = memo,
                onValueChange = { memo = it },
                modifier = Modifier.fillMaxSize(),
                placeholder = { Text("메모를 입력하세요...") },
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = Color.Transparent,
                    cursorColor = customColor,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                )
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MemoScreenPreview() {
    MemoScreen(
        initialMemo = "이 메모는 미리보기입니다.\n수정해보세요!",
        onSave = { updatedMemo ->
            println("저장된 메모: $updatedMemo")
        },
        onNavigateBack = {
            println("이전 화면으로 이동")
        }
    )
}
