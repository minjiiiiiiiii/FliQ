package com.hongul.fliq.ui.home.styles

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

object CardInfoStyles {
    object Modifiers {
        @Composable
        fun Modifier.root() = this
            .fillMaxSize()

        @Composable
        fun Modifier.container(padding: PaddingValues) = this
            .fillMaxSize()
            .padding(padding)

        @Composable
        fun Modifier.contentColumn() = this
            .fillMaxSize()
            .padding(16.dp)

        @Composable
        fun Modifier.cardImage() = this
            .fillMaxWidth()
            .aspectRatio(9f / 5f)
            .clip(RoundedCornerShape(12.dp))

        @Composable
        fun Modifier.infoCard() = this
            .fillMaxWidth()

        @Composable
        fun Modifier.infoCardContent() = this
            .fillMaxWidth()
            .padding(16.dp)

        @Composable
        fun Modifier.infoIcon() = this
            .size(24.dp)

        @Composable
        fun Modifier.infoColumn() = this

        @Composable
        fun Modifier.spacerSmall() = this
            .padding(top = 8.dp)

        @Composable
        fun Modifier.spacerMedium() = this
            .padding(top = 12.dp)

        @Composable
        fun Modifier.spacerLarge() = this
            .padding(top = 24.dp)
    }

    object Colors {
        val rootBackground = Color(0xFFF3F3F3)
        val cardElevation = 4.dp
        val infoCardBackground = Color.White
        val infoCardElevation = 2.dp
        val iconTint = Color(0xFF4C905D)
        val labelText = Color(0xFF757575)
    }
}
