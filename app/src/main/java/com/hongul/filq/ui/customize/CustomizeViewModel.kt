package com.hongul.filq.ui.customize

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hongul.filq.data.BusinessCardRepository
import com.hongul.filq.model.BusinessCard
import com.hongul.filq.model.toEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CustomizeViewModel(
    private val businessCardRepository: BusinessCardRepository
): ViewModel() {
    fun insertCard(businessCard: BusinessCard) {
        viewModelScope.launch(Dispatchers.IO) {
            businessCardRepository.insert(businessCard.toEntity())
        }
    }
}