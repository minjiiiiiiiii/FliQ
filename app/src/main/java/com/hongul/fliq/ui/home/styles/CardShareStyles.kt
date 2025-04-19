package com.hongul.fliq.ui.home.styles

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex

object CardShareStyles {
    object Modifiers {
        @Composable
        fun Modifier.root() = this
            .fillMaxSize()

        @Composable
        fun Modifier.container(padding: PaddingValues) = this
            .fillMaxSize()
            .padding(padding)

        @Composable
        fun Modifier.content() = this
            .fillMaxSize()
            .padding(32.dp)

        @Composable
        fun Modifier.shareContainer() = this
            .fillMaxWidth()

        @Composable
        fun Modifier.shareContent() = this
            .fillMaxWidth()
            .padding(16.dp)

        @Composable
        fun Modifier.shareGuideImageContainer(scope: ColumnScope) = with(scope) {
            this@shareGuideImageContainer
                .weight(1f, fill = false)
                .aspectRatio(1f)
        }

        @Composable
        fun Modifier.shareGuideImage(scope: BoxScope) = with(scope) {
            this@shareGuideImage
                .fillMaxHeight(0.3f)
                .aspectRatio(1f)
                .align(Alignment.Center)
        }

        @Composable
        fun Modifier.cardImageContainer(scope: ColumnScope) = with(scope) {
            this@cardImageContainer
                .fillMaxWidth()
                .weight(1f, fill = false)
                .aspectRatio(9f / 5f)
        }

        @Composable
        fun Modifier.cardImage() = this
            .fillMaxSize()
    }

    object Colors {
        val rootBackground = Color(0xFFF3F3F3)
        val surfaceBackground = Color.White
    }
}