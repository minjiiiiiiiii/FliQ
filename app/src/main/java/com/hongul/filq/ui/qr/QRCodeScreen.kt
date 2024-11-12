package com.hongul.filq.ui.qr

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import kotlinx.serialization.Serializable

@Serializable
data class QRCodeRoute(
    val cardId: Int
)

@Composable
fun QRCodeScreen(
    cardId: Int,
    viewModel: QRCodeViewModel
) {
    val card = viewModel.getCard(cardId)

    Scaffold(

    ) { innerPadding ->

    }
}