package com.hongul.fliq.ui.navigation.styles

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

object NavigationStyles {
    object Modifiers {
        @Composable
        fun Modifier.container() = this
            .fillMaxWidth()
            .height(72.dp)
            .background(Colors.navBackground)
            .drawWithContent {
                drawContent()
                drawLine(
                    color = Colors.navTopBorder,
                    start = Offset(0f, 0f),
                    end = Offset(size.width, 0f),
                    strokeWidth = 1f,
                )
            }

        @Composable
        fun Modifier.item(onClick: () -> Unit) = this
            .size(48.dp)
            .border(0.dp, Color.Transparent, CircleShape)
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null,
                onClick = onClick
            )
    }

    object Colors {
        val navBackground = Color.White
        val navTopBorder = Color(0xFFD7D7D7)
        val selected = Color(0xFF4C905D)
        val unselected = Color(0xFFBEBEBE)
    }

    object Animations {
        
    }
}