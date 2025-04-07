package com.hongul.fliq.ui.home.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hongul.fliq.ui.home.styles.HomeStyles
import com.hongul.fliq.ui.home.styles.HomeStyles.Modifiers.actionItem
import com.hongul.fliq.ui.home.styles.HomeStyles.Modifiers.actionItemIcon
import com.hongul.fliq.ui.home.styles.HomeStyles.Modifiers.actions
import com.hongul.fliq.ui.home.styles.HomeStyles.Modifiers.actionsInner
import com.hongul.fliq.ui.home.styles.HomeStyles.Modifiers.label
import com.hongul.fliq.ui.home.styles.HomeStyles.Modifiers.labelInner

@Composable
fun CardActionListView(items: @Composable ColumnScope.() -> Unit) {
    Surface(
        modifier = Modifier.actions(),
        shape = RoundedCornerShape(24.dp),
        color = HomeStyles.Colors.actionListBackground
    ) {
        Column(
            modifier = Modifier.actionsInner(),
            content = items
        )
    }
}

@Composable
fun CardActionItem(
    title: String,
    @DrawableRes icon: Int,
    label: @Composable () -> Unit = {},
    onClick: () -> Unit = {}
) {
    Row(
        modifier = Modifier.actionItem(onClick = onClick),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Box {
            Icon(
                painter = painterResource(icon),
                contentDescription = "",
                modifier = Modifier.actionItemIcon(),
                tint = HomeStyles.Colors.actionIcon
            )
        }
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                text = title,
                fontSize = 16.sp
            )
            label()
        }
    }
}

@Composable
fun CardActionLabel(title: String) {
    Surface(
        modifier = Modifier.label(),
        shape = RoundedCornerShape(50f)
    ) {
        Box(modifier = Modifier.labelInner()) {
            Text(
                text = title,
                color = HomeStyles.Colors.actionLabelContent,
                fontWeight = FontWeight.Bold,
                fontSize = 12.sp
            )
        }
    }
}