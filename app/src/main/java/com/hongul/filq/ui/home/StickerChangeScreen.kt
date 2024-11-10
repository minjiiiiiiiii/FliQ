package com.hongul.filq.ui.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
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
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.github.skydoves.colorpicker.compose.AlphaSlider
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
                .verticalScroll(scrollState),
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                when (screenType) {
                    ScreenType.StickerType -> {
                        StickerTypeChangeScreen(
                            stickerRes,
                            sticker,
                            onChange = { sticker = it },
                            onNext = { screenType = ScreenType.StickerColor }
                        )
                    }

                    ScreenType.StickerColor -> {
                        StickerColorChangeScreen(
                            stickerRes,
                            sticker,
                            onChange = { sticker = it },
                            onComplete = {
                                navigator.navigate("home") {
                                    popUpTo(navigator.graph.startDestinationId) {
                                        inclusive = true
                                    }
                                }
                            }
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun StickerTypeChangeScreen(
    stickerRes: ImageBitmap,
    sticker: Sticker,
    onChange: (Sticker) -> Unit,
    onNext: () -> Unit
) {
    val stickers = (0 until 16).map {
        Sticker(it, Color.LightGray)
    }

    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp),
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

        ItemGridPicker(stickers, 4, 4, 16.dp) {
            Surface(
                shape = CircleShape,
                modifier = Modifier.clickable { onChange(it) }
            ) {
                StickerPreview(
                    stickerRes,
                    x = it.x,
                    y = it.y,
                    size = it.size,
                    stickerColor = it.color
                )
            }
        }
    }

    Button(
        onClick = onNext,
        modifier = Modifier.fillMaxWidth(),
        colors = ButtonDefaults.buttonColors(
            containerColor = PrimaryDark
        )
    ) {
        Text(
            "다음",
            modifier = Modifier.padding(vertical = 8.dp)
        )
    }
}

@Composable
fun StickerColorChangeScreen(
    stickerRes: ImageBitmap,
    sticker: Sticker,
    onChange: (Sticker) -> Unit,
    onComplete: () -> Unit
) {
    val colorPickerController = rememberColorPickerController()

    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp),
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

        HsvColorPicker(
            modifier = Modifier.size(200.dp),
            controller = colorPickerController,
            onColorChanged = { onChange(sticker.copy(color = it.color)) }
        )

        BrightnessSlider(
            modifier = Modifier
                .fillMaxWidth()
                .height(16.dp),
            controller = colorPickerController,
        )
    }

    Button(
        onClick = onComplete,
        modifier = Modifier.fillMaxWidth(),
        colors = ButtonDefaults.buttonColors(
            containerColor = PrimaryDark
        )
    ) {
        Text(
            "마침",
            modifier = Modifier.padding(vertical = 8.dp)
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
        modifier = Modifier.size(72.dp)
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

@Composable
private fun <T> ItemGridPicker(
    items: List<T>,
    row: Int,
    column: Int,
    gap: Dp,
    content: @Composable (item: T) -> Unit
) {
    Box(contentAlignment = Alignment.Center) {
        Column(
            verticalArrangement = Arrangement.spacedBy(gap)
        ) {
            for (i in 0 until row) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(gap)
                ) {
                    for (j in 0 until column) {
                        content(items[i * column + j])
                    }
                }
            }
        }
    }
}