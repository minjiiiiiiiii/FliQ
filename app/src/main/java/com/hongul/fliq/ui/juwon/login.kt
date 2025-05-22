package com.hongul.fliq.ui.juwon

import com.hongul.fliq.R
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

val PlayfairFont = FontFamily(
    Font(R.font.playfair_display_black, FontWeight.Normal),
    Font(R.font.playfair_bold, FontWeight.Bold)
)
val PretendardFont = FontFamily(
    Font(R.font.pretendard_regular, FontWeight.Normal),
    Font(R.font.pretendard_semibold, FontWeight.Bold),
)

@Composable
fun FliQLoginScreen(modifier: Modifier = Modifier) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // FliQ 로고
        Text(
            text = "FliQ",
            style = TextStyle(
                fontFamily = PlayfairFont,
                fontSize = 80.sp,
                fontWeight = FontWeight.Bold
            )
        )

        Spacer(modifier = Modifier.height(25.dp))

        // 설명 문구
        Text(
            text = "터치 한 번으로 빠르게 명함 전달",
            style = TextStyle(
                fontSize = 14.sp,
                color = Color.Black,
                fontFamily = PretendardFont,
                fontWeight = FontWeight.Bold
            )
        )

        Spacer(modifier = Modifier.height(40.dp))

        KakaoLoginButton()
    }
}

@Composable
fun KakaoLoginButton() {
    Button(
        onClick = { /* TODO: 카카오 로그인 기능 */ },
        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFFE812)),
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier
            .fillMaxWidth(0.85f)
            .height(50.dp)
    ) {
        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ju_kakao_logo),
                    contentDescription = "Kakao Logo",
                    modifier = Modifier
                        .size(24.dp)
                        .align(Alignment.CenterVertically)
                )
            }
            Text(
                text = "카카오톡 계정으로 로그인",
                color = Color.Black,
                fontSize = 15.sp,
                fontFamily = PretendardFont,
                modifier = Modifier.align(Alignment.Center)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewFliQLoginScreen() {
    FliQLoginScreen()
}
