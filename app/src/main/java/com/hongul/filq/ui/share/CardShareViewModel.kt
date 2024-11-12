package com.hongul.filq.ui.share

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import com.hongul.filq.data.BusinessCardRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn

class CardShareViewModel(
    cardId: Int,
    businessCardRepository: BusinessCardRepository
): ViewModel() {
    val selectedCard = businessCardRepository
        .getBusinessCard(cardId)
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = null
        )

    companion object {
        val CARD_ID = object : CreationExtras.Key<Int> {}
    }
}