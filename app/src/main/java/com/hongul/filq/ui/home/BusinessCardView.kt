package com.hongul.filq.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.hongul.filq.ui.theme.PrimaryDeepDark

@Composable
fun EmptyBusinessCard() {
    BusinessCardLayout(
        businessCardView = {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(PrimaryDeepDark)
                    .clickable {  },
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(
                    Icons.Filled.Add,
                    contentDescription = "명함 추가",
                    modifier = Modifier
                        .padding(8.dp)
                        .size(32.dp)
                    ,
                    tint = Color.White
                )
                Text(
                    "나만의 브랜드를 담은\n나만의 명함을 직접 만들어보세요.",
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.White
                )
            }
        }
    )
}

@Composable
private fun BusinessCardLayout(
    businessCardView: @Composable () -> Unit,
    profileView: @Composable () -> Unit = {},
    introductionView: @Composable () -> Unit = {},
) {
    ElevatedCard(
        shape = RoundedCornerShape(20.dp),
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            ElevatedCard(
                shape = RoundedCornerShape(20.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(9f / 5f)
            ) {
                businessCardView()
            }

            Text(
                "프로필",
                fontWeight = FontWeight.Medium,
                modifier = Modifier
                    .padding(start = 16.dp, top = 16.dp, bottom = 8.dp)
            )
            ElevatedCard(
                shape = RoundedCornerShape(20.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(9f / 5f)
            ) {
                profileView()
            }

            Text(
                "소개",
                fontWeight = FontWeight.Medium,
                modifier = Modifier
                    .padding(start = 16.dp, top = 16.dp, bottom = 8.dp)
            )

            ElevatedCard(
                shape = RoundedCornerShape(20.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .defaultMinSize(minHeight = 240.dp)
            ) {
                introductionView()
            }
        }
    }
}