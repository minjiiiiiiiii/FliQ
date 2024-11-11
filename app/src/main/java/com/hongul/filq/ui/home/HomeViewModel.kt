package com.hongul.filq.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hongul.filq.data.BusinessCardRepository
import com.hongul.filq.model.Avatar
import com.hongul.filq.model.BusinessCard
import com.hongul.filq.model.toEntity
import com.hongul.filq.model.toModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class HomeViewModel(private val businessCardRepository: BusinessCardRepository): ViewModel() {
    val businessCards = businessCardRepository.getAllBusinessCard()
        .map { cardList ->
            cardList.map { cardEntity ->
                cardEntity.toModel()
            }
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    fun delete(businessCard: BusinessCard) {
        viewModelScope.launch(Dispatchers.IO) {
            businessCardRepository.delete(businessCard.toEntity())
        }
    }

    fun insertCard(businessCard: BusinessCard) {
        viewModelScope.launch(Dispatchers.IO) {
            businessCardRepository.insert(businessCard.toEntity())
        }
    }

    fun updateAvatar(cardId: Int, avatar: Avatar) {
        viewModelScope.launch(Dispatchers.IO) {
            businessCardRepository.updateAvatar(cardId, avatar)
        }
    }
}