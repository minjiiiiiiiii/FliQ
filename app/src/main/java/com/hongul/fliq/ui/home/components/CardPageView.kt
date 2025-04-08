package com.hongul.fliq.ui.home.components

import android.graphics.BitmapFactory
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hongul.fliq.R
import com.hongul.fliq.ui.home.styles.HomeStyles
import com.hongul.fliq.ui.home.styles.HomeStyles.Modifiers.cardContainer
import com.hongul.fliq.ui.home.styles.HomeStyles.Modifiers.cardPage
import com.hongul.fliq.ui.home.styles.HomeStyles.Modifiers.createCardIcon
import com.hongul.fliq.ui.home.styles.HomeStyles.Modifiers.createCardInner
import com.hongul.fliq.ui.home.styles.HomeStyles.Modifiers.progressIndicator

@Composable
fun CardPageView(
    // TODO: 실제 ImageFile 주입
    cardImagePath: String? = null,
    innerContentMode: InnerContentMode,
    onClickCard: () -> Unit = {},
    onClickInfo: () -> Unit = {},
    onClickShare: () -> Unit = {},
    onClickChatbot: () -> Unit = {}
) {
    CardPageLayout(
        onClickCard = onClickCard,
        innerContentMode = innerContentMode,
        card = {
            // TODO: 명함 예제 이미지 없애기
            val imageResource = if(cardImagePath == null) ImageBitmap.imageResource(R.drawable.img_card_example)
            else BitmapFactory
                .decodeFile(cardImagePath)
                .asImageBitmap()

            Image(
                bitmap = imageResource,
                contentDescription = null,
                contentScale = ContentScale.Crop
            )

        }
    ) {
        CardActionListView {
            CardActionItem(
                title = "정보",
                icon = R.drawable.ic_action_info,
                onClick = onClickInfo
            )
            CardActionItem(
                title = "공유하기",
                icon = R.drawable.ic_action_share,
                onClick = onClickShare
            )
            CardActionItem(
                title = "챗봇+",
                icon = R.drawable.ic_action_chatbot,
                onClick = onClickChatbot,
                label = {
                    CardActionLabel("New")
                }
            )
        }
    }
}

@Composable
fun CreateCardPageView(
    onClick: () -> Unit = {}
) {
    CardPageLayout(
        onClickCard = onClick,
        innerContentMode = InnerContentMode.HIDE,
        card = {
            Column(
                modifier = Modifier.createCardInner(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(
                    painter = painterResource(R.drawable.ic_add_card),
                    contentDescription = "명함 추가",
                    modifier = Modifier.createCardIcon(),
                    tint = HomeStyles.Colors.createCardContent
                )
                Text(
                    "나만의 브랜드를 담은\n나만의 명함을 직접 만들어보세요.",
                    textAlign = TextAlign.Center,
                    color = HomeStyles.Colors.createCardContent,
                    fontSize = 16.sp
                )
            }
        }
    )
}

@Composable
private fun CardPageLayout(
    onClickCard: () -> Unit = {},
    innerContentMode: InnerContentMode,
    card: @Composable () -> Unit,
    content: @Composable ColumnScope.() -> Unit = {}
) {
    Column(
        modifier = Modifier.cardPage(),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Surface(
            modifier = Modifier.cardContainer(onClick = onClickCard),
            shape = RoundedCornerShape(24.dp),
            color = Color.Transparent
        ) {
            card()
        }

        when (innerContentMode) {
            InnerContentMode.SHOW -> content()
            InnerContentMode.PENDING -> CircularProgressIndicator(
                modifier = Modifier.progressIndicator()
            )
            InnerContentMode.HIDE -> {}
        }
    }
}

enum class InnerContentMode {
    SHOW, PENDING, HIDE
}