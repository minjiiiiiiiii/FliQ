package com.hongul.filq.ui.login

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.graphics.SolidColor

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun LoginScreen(
    onBackPress: () -> Unit = {},
    onLoginClick: () -> Unit = {},
    onPasswordResetClick: () -> Unit = {}
) {
    val customGreen = Color(0xFF125422)
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "") },
                navigationIcon = {
                    IconButton(onClick = onBackPress) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "뒤로 가기",
                            tint = Color.Black
                        )
                    }
                },
                colors = TopAppBarDefaults.smallTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.background
                )
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp)
                .padding(innerPadding),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            Spacer(modifier = Modifier.height(150.dp))

            Text(
                text = "FliQ",
                style = MaterialTheme.typography.displayLarge.copy(
                    color = customGreen,
                    fontWeight = FontWeight.Bold
                ),
                modifier = Modifier.padding(bottom = 32.dp)
            )

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 24.dp)
            ) {
                Text(
                    text = "이메일",
                    style = MaterialTheme.typography.bodyMedium.copy(color = customGreen),
                    modifier = Modifier.padding(bottom = 8.dp, start = 16.dp)
                )
                BasicTextField(
                    value = email,
                    onValueChange = { email = it },
                    textStyle = TextStyle(
                        fontSize = 16.sp,
                        color = Color.Black
                    ),
                    cursorBrush = SolidColor(customGreen),
                    decorationBox = { innerTextField ->
                        Column {
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(start = 16.dp, top = 4.dp, bottom = 4.dp)
                            ) {
                                if (email.isEmpty()) {
                                    Text(
                                        text = "이메일 입력",
                                        style = TextStyle(color = Color.Gray.copy(alpha = 0.6f))
                                    )
                                }
                                innerTextField()
                            }
                            Spacer(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(2.dp)
                                    .background(customGreen)
                            )
                        }
                    },
                    modifier = Modifier.fillMaxWidth()
                )
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp)
            ) {
                Text(
                    text = "비밀번호",
                    style = MaterialTheme.typography.bodyMedium.copy(color = customGreen),
                    modifier = Modifier.padding(bottom = 8.dp, start = 16.dp)
                )
                BasicTextField(
                    value = password,
                    onValueChange = { password = it },
                    textStyle = TextStyle(
                        fontSize = 16.sp,
                        color = Color.Black
                    ),
                    cursorBrush = SolidColor(customGreen),
                    decorationBox = { innerTextField ->
                        Column {
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(start = 16.dp, top = 4.dp, bottom = 4.dp)
                            ) {
                                if (password.isEmpty()) {
                                    Text(
                                        text = "비밀번호 입력",
                                        style = TextStyle(color = Color.Gray.copy(alpha = 0.6f))
                                    )
                                }
                                innerTextField()
                            }
                            Spacer(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(2.dp)
                                    .background(customGreen)
                            )
                        }
                    },
                    modifier = Modifier.fillMaxWidth()
                )
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "비밀번호를 잊으셨나요?",
                    style = MaterialTheme.typography.bodySmall.copy(color = Color.Black)
                )
                Spacer(modifier = Modifier.width(4.dp))
                TextButton(onClick = onPasswordResetClick, contentPadding = PaddingValues(0.dp)) {
                    Text(
                        text = "비밀번호 재설정",
                        style = MaterialTheme.typography.bodySmall.copy(
                            color = customGreen,
                            textDecoration = TextDecoration.Underline
                        )
                    )
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            // Login Button
            Button(
                onClick = onLoginClick,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp),
                colors = ButtonDefaults.buttonColors(containerColor = customGreen)
            ) {
                Text(
                    text = "로그인",
                    color = Color.White,
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewLoginScreen() {
    LoginScreen()
}
