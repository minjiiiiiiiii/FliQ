package com.hongul.filq.api

import android.content.Context
import com.google.android.gms.nearby.connection.ConnectionInfo
import com.google.android.gms.nearby.connection.ConnectionLifecycleCallback
import com.google.android.gms.nearby.connection.ConnectionResolution
import com.google.android.gms.nearby.connection.ConnectionsStatusCodes
import com.google.android.gms.nearby.connection.PayloadCallback
import com.hongul.filq.api.NearbyApi.acceptConnection
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class ImmediateAcceptConnection(
    private val context: Context,
    private val coroutineScope: CoroutineScope, // ViewModelScope 넣을 것
    private val callback: PayloadCallback,
    private val onAcceptSuccess: () -> Unit = {},
    private val onAcceptFailure: () -> Unit = {},

): ConnectionLifecycleCallback() {
    override fun onConnectionInitiated(id: String, info: ConnectionInfo) {
        coroutineScope.launch {
            acceptConnection(
                context, id, callback,
                onSuccess = onAcceptSuccess,
                onFailure = onAcceptFailure
            )
        }
    }

    override fun onConnectionResult(id: String, resolution: ConnectionResolution) {
        resolution.status.statusCode
        ConnectionsStatusCodes.SUCCESS
        TODO("Not yet implemented")
    }

    override fun onDisconnected(id: String) {
        TODO("Not yet implemented")
    }
}