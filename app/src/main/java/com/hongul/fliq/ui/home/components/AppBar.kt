package com.hongul.fliq.ui.home.components

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hongul.fliq.ui.home.styles.HomeStyles
import com.hongul.fliq.ui.home.styles.HomeStyles.Modifiers.appBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBar(
    title: String,
    actions: Map<ImageVector, () -> Unit> = mapOf()
) {
    val localConfig = LocalConfiguration.current
    val heightDp = localConfig.screenHeightDp

    TopAppBar(
        modifier = Modifier.appBar(),
        title = {
            Text(text = title, fontWeight = FontWeight.Bold, fontSize = 24.sp)
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = HomeStyles.Colors.rootBackground
        ),
        expandedHeight =
            if (heightDp <= 840) 80.dp
            else 120.dp,
        actions = {
            for((icon, onClick) in actions) {
                IconButton(onClick = onClick) {
                    Icon(icon, "")
                }
            }
        }
    )
}