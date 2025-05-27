package com.hongul.fliq.ui.home.pages

import CardInfoContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.hongul.fliq.model.card.Card
import com.hongul.fliq.ui.home.components.AppBar
import com.hongul.fliq.ui.home.styles.CardInfoStyles
import com.hongul.fliq.ui.home.styles.CardInfoStyles.Modifiers.container
import com.hongul.fliq.ui.home.styles.CardInfoStyles.Modifiers.root

@Composable
fun CardInfoScreen(
    navigator: NavHostController,
    cardId: Int
) {
    // 카드 정보를 저장할 State
    var card by remember { mutableStateOf<Card?>(null) }

    // 실제 구현에서는 여기서 카드 정보를 가져오는 로직이 필요합니다
    LaunchedEffect(cardId) {
        // 예시 데이터 - 실제로는 ViewModel이나 Repository에서 가져와야 합니다
        card = Card(
            id = cardId,
            owner = 1,
            name = "홍길동",
            email = "hong@example.com",
            contact = "010-1234-5678",
            profileImageURL = "https://example.com/profile.jpg",
            cardImageURL = "https://example.com/card.jpg",
            organization = "ABC 회사",
            position = "소프트웨어 개발자",
            private = false
        )
    }

    Scaffold(
        modifier = Modifier.root(),
        containerColor = CardInfoStyles.Colors.rootBackground,
        topBar = {
            AppBar(
                title = "FliQ",
                actions = mapOf()
            )
        }
    ) { padding ->
        Box(
            modifier = Modifier.container(padding)
        ) {
            card?.let { cardData ->
                CardInfoContent(cardData)
            } ?: run {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text("카드 정보를 불러오는 중...")
                }
            }
        }
    }
}
