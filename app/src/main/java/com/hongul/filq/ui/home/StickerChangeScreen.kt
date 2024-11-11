package com.hongul.filq.ui.home

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.github.skydoves.colorpicker.compose.BrightnessSlider
import com.github.skydoves.colorpicker.compose.HsvColorPicker
import com.github.skydoves.colorpicker.compose.rememberColorPickerController
import com.hongul.filq.R
import com.hongul.filq.model.Sticker
import com.hongul.filq.ui.theme.PrimaryDark
import kotlinx.serialization.Serializable

@Serializable
data class StickerChangeRoute(
    val cardId: String
)

private enum class ScreenType {
    StickerType, StickerColor
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StickerChangeScreen(cardId: String, navigator: NavHostController) {
    val stickerRes = ImageBitmap.imageResource(R.drawable.stickers)

    val scrollState = rememberScrollState()
    var screenType by remember { mutableStateOf(ScreenType.StickerType) }
    var sticker by remember { mutableStateOf(Sticker(0, Color.LightGray)) }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "스티커 등록",
                        fontSize = 20.sp,
                        modifier = Modifier.padding(horizontal = 10.dp)
                    )
                },
                navigationIcon = {
                    if (screenType == ScreenType.StickerColor) {
                        IconButton(
                            onClick = { screenType = ScreenType.StickerType }
                        ) {
                            Icon(
                                Icons.AutoMirrored.Filled.KeyboardArrowLeft,
                                contentDescription = "뒤로가기"
                            )
                        }
                    }
                }
            )
        }
    ) { innerPadding ->
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.SpaceBetween,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Column(modifier = Modifier.fillMaxWidth()) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(180.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        StickerPreview(
                            stickerRes,
                            x = sticker.x,
                            y = sticker.y,
                            size = sticker.size,
                            stickerColor = sticker.color
                        )
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    ElevatedCard(modifier = Modifier.fillMaxWidth()) {
                        when(screenType) {
                            ScreenType.StickerType -> StickerTypeChange(stickerRes) { sticker = it }
                            ScreenType.StickerColor -> StickerColorChange(sticker) { sticker = it }
                        }
                    }
                }

                Button(
                    onClick = {
                        when(screenType) {
                            ScreenType.StickerType -> {
                                screenType = ScreenType.StickerColor
                            }
                            ScreenType.StickerColor -> {
                                navigator.navigate("home") {
                                    popUpTo(navigator.graph.startDestinationId) {
                                        inclusive = true
                                    }
                                }
                            }
                        }
                    },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = PrimaryDark
                    )
                ) {
                    Text(
                        when(screenType) {
                            ScreenType.StickerType -> "다음"
                            ScreenType.StickerColor -> "완료"
                        },
                        modifier = Modifier.padding(vertical = 8.dp)
                    )
                }
            }
        }
    }

    BackHandler {
        when(screenType) {
            ScreenType.StickerType -> {
                navigator.popBackStack()
            }
            ScreenType.StickerColor -> {
                screenType = ScreenType.StickerType
            }
        }
    }
}

@Composable
private fun StickerTypeChange(
    stickerRes: ImageBitmap,
    onChange: (Sticker) -> Unit
) {
    val stickers: List<Sticker> = (0 until 16).map { Sticker(it, Color.LightGray) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text("스티커 종류", modifier = Modifier.padding(start = 16.dp))

        ElevatedCard(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                contentAlignment = Alignment.Center
            ) {
                Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                    for (i in 0 until 4) {
                        Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                            for (j in 0 until 4) {
                                val current = stickers[i * 4 + j]

                                Box(modifier = Modifier.clickable { onChange(current.copy()) }) {
                                    StickerPreview(
                                        stickerRes,
                                        x = current.x,
                                        y = current.y,
                                        size = current.size,
                                        stickerColor = current.color
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
private fun StickerColorChange(
    sticker: Sticker,
    onChange: (Sticker) -> Unit
) {
    val colorPickerController = rememberColorPickerController()

    LaunchedEffect(Unit) {
        colorPickerController.selectByColor(Color.LightGray, false)
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text("색상", modifier = Modifier.padding(start = 16.dp))

        ElevatedCard(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            HsvColorPicker(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .padding(16.dp),
                controller = colorPickerController,
                onColorChanged = { onChange(sticker.copy(color = it.color)) },
                initialColor = Color.LightGray
            )
        }

        Text("밝기", modifier = Modifier.padding(start = 16.dp))

        BrightnessSlider(
            modifier = Modifier
                .fillMaxWidth()
                .height(24.dp),
            controller = colorPickerController,
        )
    }
}

@Composable
private fun StickerPreview(
    stickerRes: ImageBitmap,
    x: Int,
    y: Int,
    size: Int,
    stickerColor: Color
) {
    Surface(
        shape = CircleShape,
        color = stickerColor,
        modifier = Modifier.size(72.dp),
        shadowElevation = 4.dp
    ) {
        Image(
            BitmapPainter(
                stickerRes,
                IntOffset(x, y),
                IntSize(size, size)
            ),
            contentDescription = "스티커",
            modifier = Modifier
                .padding(4.dp)
                .fillMaxSize(),
            contentScale = ContentScale.Fit
        )
    }
}