package com.hongul.fliq.ui.home.styles

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

object HomeStyles {
    object Modifiers {
        @Composable
        fun Modifier.appBar() = this
            .padding(16.dp)

        @Composable
        fun Modifier.fabIcon() = this
            .size(24.dp)

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
            .padding(top = 60.dp)

        @Composable
        fun Modifier.cardPager() = this
            .fillMaxWidth()

        @Composable
        fun Modifier.cardPage() = this
            .fillMaxWidth()

        @Composable
        fun Modifier.cardContainer(onClick: () -> Unit) = this
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .aspectRatio(9f / 5f)
            .clickable(onClick = onClick, indication = null, interactionSource = null)

        @Composable
        fun Modifier.createCardInner() = this
            .fillMaxSize()
            .background(
                Brush.linearGradient(
                    listOf(Colors.createCardBG1, Colors.createCardBG2)
                )
            )

        @Composable
        fun Modifier.createCardIcon() = this
            .padding(bottom = 16.dp)
            .size(32.dp)

        @Composable
        fun Modifier.progressIndicator() = this
            .padding(top = 60.dp)

        @Composable
        fun Modifier.actions() = this
            .fillMaxWidth()
            .padding(horizontal = 16.dp)

        @Composable
        fun Modifier.actionsInner() = this
            .fillMaxWidth()

        @Composable
        fun Modifier.actionItem(onClick: () -> Unit) = this
            .fillMaxWidth()
            .height(60.dp)
            .clickable(onClick = onClick)
            .padding(horizontal = 16.dp)
            .drawWithContent {
                drawContent()
                drawLine(
                    color = Colors.actionItemBorder,
                    start = Offset(0f, 0f),
                    end = Offset(size.width, 0f),
                    strokeWidth = 1f,
                )
            }

        @Composable
        fun Modifier.actionItemIcon() = this
            .size(24.dp)

        @Composable
        fun Modifier.label() = this

        @Composable
        fun Modifier.labelInner() = this
            .background(
                Brush.linearGradient(
                    listOf(Colors.actionLabelBG1, Colors.actionLabelBG2)
                )
            )
            .padding(vertical = 4.dp, horizontal = 6.dp)
    }

    object Colors {
        val rootBackground = Color(0xFFF3F3F3)
        val fabContainer = Color.White
        val fabContent = Color.Black
        val fabIcon = Color(0xFFBEBEBE)
        val createCardContent = Color.White
        val createCardBG1 = Color(0xFF4C905D)
        val createCardBG2 = Color(0xFF125422)
        val actionListBackground = Color.White
        val actionItemBorder = Color(0xFFD7D7D7)
        val actionIcon = Color(0xFFBEBEBE)
        val actionLabelBG1 = Color(0xFF7FBE85)
        val actionLabelBG2 = Color(0xFF4C905D)
        val actionLabelContent = Color.White
    }
}