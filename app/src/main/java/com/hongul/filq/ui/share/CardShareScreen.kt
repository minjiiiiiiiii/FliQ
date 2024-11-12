package com.hongul.filq.ui.share

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredWidthIn
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.MutableCreationExtras
import androidx.lifecycle.viewmodel.compose.viewModel
import com.hongul.filq.R
import com.hongul.filq.ui.CardShareViewModelProvider
import kotlinx.serialization.Serializable

@Serializable
data class CardShareRoute(
    val cardId: Int
)

private enum class ShareMode { NEARBY, QR }

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CardShareScreen(
    viewModel: CardShareViewModel = viewModel(
        factory = CardShareViewModelProvider.Factory,
    )
) {
    val card by viewModel.selectedCard.collectAsState()
    var shareMode by remember { mutableStateOf(ShareMode.NEARBY) }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(R.string.app_name),
                        fontSize = 20.sp,
                        modifier = Modifier.padding(horizontal = 10.dp),
                        color = Color.White
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.Black
                )
            )
        },
        containerColor = Color.Black
    ) { innerPadding ->
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            color = Color.Black
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(32.dp),
                verticalArrangement = Arrangement.spacedBy(32.dp)
            ) {
                PagerIndicator(
                    shareMode,
                    onClickNearby = { shareMode = ShareMode.NEARBY },
                    onClickQR = { shareMode = ShareMode.QR }
                )

                ElevatedCard(
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.elevatedCardColors(
                        containerColor = when(shareMode) {
                            ShareMode.NEARBY -> Color.Black
                            ShareMode.QR -> Color.White
                        },
                        contentColor = when(shareMode) {
                            ShareMode.NEARBY -> Color.White
                            ShareMode.QR -> Color.Black
                        }
                    ),
                    elevation = CardDefaults.elevatedCardElevation(defaultElevation = 4.dp)
                ) {
                    val cardImage = ImageBitmap.imageResource(R.drawable.test_card)

                    when(shareMode) {
                        ShareMode.NEARBY -> NearbyView(cardImage, shareMode)
                        ShareMode.QR -> QRView(cardImage, shareMode)
                    }
                }
            }
        }
    }
}

@Composable
private fun NearbyView(cardImage: ImageBitmap, shareMode: ShareMode) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        RotatableCardImage(cardImage, shareMode)
        Text("전송 할 기기와 서로 마주 대세요.", fontWeight = FontWeight.Normal, color = Color.White)
    }
}

@Composable
private fun QRView(cardImage: ImageBitmap, shareMode: ShareMode) {
    val qrImage = ImageBitmap.imageResource(R.drawable.test_qr)

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            Image(
                qrImage,
                contentDescription = "QR 코드",
                modifier = Modifier
                    .size(200.dp)
                    .padding(16.dp),
                contentScale = ContentScale.Fit
            )
        }
        RotatableCardImage(cardImage, shareMode)
    }
}

@Composable
private fun RotatableCardImage(cardImage: ImageBitmap, shareMode: ShareMode) {
    val angle by animateFloatAsState(
        targetValue = when(shareMode) {
            ShareMode.NEARBY -> -90f
            ShareMode.QR -> 0f
        },
        label = "angle"
    )
    val modifier = when(shareMode) {
        ShareMode.NEARBY -> Modifier.fillMaxHeight()
        ShareMode.QR -> Modifier.fillMaxWidth()
    }

    Box(modifier = modifier) {
        Image(
            cardImage,
            contentDescription = "명함 이미지",
            modifier = Modifier
                .fillMaxSize()
                .rotate(angle),
            contentScale = ContentScale.Fit
        )
    }
}

@Composable
private fun PagerIndicator(
    shareMode: ShareMode,
    onClickNearby: () -> Unit,
    onClickQR: () -> Unit
) {
    val buttonColor = @Composable { mode: ShareMode, targetMode: ShareMode ->
        when(mode) {
            targetMode -> ButtonDefaults.buttonColors(
                containerColor = Color.Gray,
                contentColor = Color.White
            )
            else -> ButtonDefaults.buttonColors(
                containerColor = Color.Black,
                contentColor = Color.LightGray
            )
        }
    }

    val fontWeight = @Composable { mode: ShareMode, targetMode: ShareMode ->
        when(mode) {
            targetMode -> FontWeight.Bold
            else -> FontWeight.SemiBold
        }
    }

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        Button(
            onClick = onClickNearby,
            modifier = Modifier
                .weight(1f),
            colors = buttonColor(shareMode, ShareMode.NEARBY)
        ) {
            Text(
                "Nearby",
                modifier = Modifier.padding(4.dp),
                fontWeight = fontWeight(shareMode, ShareMode.NEARBY)
            )
        }
        Button(
            onClick = onClickQR,
            modifier = Modifier
                .weight(1f),
            colors = buttonColor(shareMode, ShareMode.QR)
        ) {
            Text(
                "QR 스캔",
                modifier = Modifier.padding(4.dp),
                fontWeight = fontWeight(shareMode, ShareMode.QR)
            )
        }
    }
}