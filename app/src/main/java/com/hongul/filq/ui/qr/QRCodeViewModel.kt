package com.hongul.filq.ui.qr

import androidx.lifecycle.ViewModel
import com.hongul.filq.data.BusinessCardRepository

class QRCodeViewModel(private val businessCardRepository: BusinessCardRepository): ViewModel() {
    fun getCard(cardId: Int) = businessCardRepository.getBusinessCard(cardId)
}