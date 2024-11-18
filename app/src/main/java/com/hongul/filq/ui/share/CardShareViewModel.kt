package com.hongul.filq.ui.share

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.gms.nearby.connection.Payload
import com.hongul.filq.api.NearbyApi.sendPayload
import com.hongul.filq.api.NearbyApi.startAdvertising
import com.hongul.filq.api.NearbyApi.stopAdvertising
import com.hongul.filq.api.hostConnectionCallback
import com.hongul.filq.data.BusinessCardRepository
import com.hongul.filq.model.toModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class CardShareViewModel(
    cardId: Int,
    businessCardRepository: BusinessCardRepository
): ViewModel() {
    val selectedCard = businessCardRepository
        .getBusinessCard(cardId)
        .map { it?.toModel() }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = null
        )

    fun startAdvertising(
        context: Context,
        onAfterSending: (reply: String) -> Unit = {},
    ) {
        viewModelScope.launch {
            startAdvertising(
                context = context,
                name = selectedCard.value!!.name,
                callback = hostConnectionCallback(
                    context = context,
                    coroutineScope = viewModelScope,
                    onReady = { id ->
                        val json = Json.encodeToString(selectedCard.value!!)
                        val payload = Payload.fromBytes(json.toByteArray())
                        sendPayload(
                            context = context,
                            id = id,
                            payload = payload
                        )
                    },
                    onAfterSending = onAfterSending
                )
            )
        }
    }

    fun stopAdvertising(context: Context) {
        viewModelScope.launch {
            stopAdvertising(context)
        }
    }
}