package com.hongul.filq.ui.home

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.gms.nearby.connection.DiscoveredEndpointInfo
import com.google.android.gms.nearby.connection.EndpointDiscoveryCallback
import com.hongul.filq.api.NearbyApi.requestConnection
import com.hongul.filq.api.NearbyApi.startAdvertising
import com.hongul.filq.api.NearbyApi.startDiscovering
import com.hongul.filq.api.clientConnectionCallback
import com.hongul.filq.api.hostConnectionCallback
import com.hongul.filq.data.BusinessCardRepository
import com.hongul.filq.model.Avatar
import com.hongul.filq.model.BusinessCard
import com.hongul.filq.model.toEntity
import com.hongul.filq.model.toModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class HomeViewModel(
    private val businessCardRepository: BusinessCardRepository
): ViewModel() {
    private val _shareRequest = MutableStateFlow<Pair<String, String>?>(null)
    val shareRequest = _shareRequest.asStateFlow()

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

    fun sendCard(context: Context, businessCard: BusinessCard) {
        viewModelScope.launch {
            startAdvertising(
                context = context,
                name = businessCard.name,
                callback = hostConnectionCallback(context, viewModelScope)
            )
        }
    }

    fun startDiscovering(context: Context) {
        viewModelScope.launch {
            startDiscovering(
                context = context,
                callback = object: EndpointDiscoveryCallback() {
                    override fun onEndpointFound(id: String, info: DiscoveredEndpointInfo) {
                        _shareRequest.tryEmit(id to info.endpointName)
                    }

                    override fun onEndpointLost(p0: String) { }
                }
            )
        }
    }

    fun requestConnection(context: Context, name: String) {
        viewModelScope.launch {
            requestConnection(
                context = context,
                name = name,
                id = shareRequest.value!!.first,
                callback = clientConnectionCallback(context, viewModelScope)
            )
        }
    }
}