package com.hongul.filq.ui.contact

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hongul.filq.data.BusinessCardRepository
import com.hongul.filq.model.toModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class ContactViewModel(
    private val businessCardRepository: BusinessCardRepository
): ViewModel() {
    val acceptedCards = businessCardRepository.getAllBusinessCard()
        .map { cardList ->
            cardList.map { cardEntity ->
                cardEntity.toModel()
            }.filter { card ->
                card.owner != 2
            }
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )
}