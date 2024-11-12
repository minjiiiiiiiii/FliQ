package com.hongul.filq.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import com.hongul.filq.R

val Pretendard = FontFamily(
    Font(R.font.pretendard_thin, FontWeight.Thin),
    Font(R.font.pretendard_extralight, FontWeight.ExtraLight),
    Font(R.font.pretendard_light, FontWeight.Light),
    Font(R.font.pretendard_regular, FontWeight.Normal),
    Font(R.font.pretendard_medium, FontWeight.Medium),
    Font(R.font.pretendard_semibold, FontWeight.SemiBold),
    Font(R.font.pretendard_bold, FontWeight.Bold),
    Font(R.font.pretendard_extrabold, FontWeight.ExtraBold),
    Font(R.font.pretendard_black, FontWeight.Black)
)

// Set of Material typography styles to start with
val Typography = Typography(
    displayLarge = TextStyle(
        fontFamily = Pretendard
    ),
    displayMedium = TextStyle(
        fontFamily = Pretendard
    ),
    displaySmall = TextStyle(
        fontFamily = Pretendard
    ),
    headlineLarge = TextStyle(
        fontFamily = Pretendard
    ),
    headlineMedium = TextStyle(
        fontFamily = Pretendard
    ),
    headlineSmall = TextStyle(
        fontFamily = Pretendard
    ),
    titleLarge = TextStyle(
        fontFamily = Pretendard
    ),
    titleMedium = TextStyle(
        fontFamily = Pretendard
    ),
    titleSmall = TextStyle(
        fontFamily = Pretendard
    ),
    bodyLarge = TextStyle(
        fontFamily = Pretendard
    ),
    bodyMedium = TextStyle(
        fontFamily = Pretendard
    ),
    bodySmall = TextStyle(
        fontFamily = Pretendard
    ),
    labelLarge = TextStyle(
        fontFamily = Pretendard
    ),
    labelMedium = TextStyle(
        fontFamily = Pretendard
    ),
    labelSmall = TextStyle(
        fontFamily = Pretendard
    )
)