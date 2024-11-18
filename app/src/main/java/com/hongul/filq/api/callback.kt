package com.hongul.filq.api

import android.content.Context
import com.google.android.gms.nearby.connection.ConnectionInfo
import com.google.android.gms.nearby.connection.ConnectionLifecycleCallback
import com.google.android.gms.nearby.connection.ConnectionResolution
import com.google.android.gms.nearby.connection.ConnectionsStatusCodes
import com.google.android.gms.nearby.connection.Payload
import com.google.android.gms.nearby.connection.PayloadCallback
import com.google.android.gms.nearby.connection.PayloadTransferUpdate
import com.hongul.filq.api.NearbyApi.acceptConnection
import com.hongul.filq.api.NearbyApi.sendPayload
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

inline fun hostConnectionCallback(
    context: Context,
    coroutineScope: CoroutineScope,
    crossinline onReady: CoroutineScope.(id: String) -> Unit = {},
    crossinline onError: () -> Unit = {},
    noinline onAfterSending: (reply: String) -> Unit = {},
) = object: ConnectionLifecycleCallback() {
    override fun onConnectionInitiated(id: String, info: ConnectionInfo) {
        coroutineScope.launch {
            acceptConnection(
                context = context,
                id = id,
                callback = object: PayloadCallback() {
                    override fun onPayloadReceived(id: String, payload: Payload) {
                        val bytes = payload.asBytes()!!
                        val reply = bytes.toString(Charsets.UTF_8)
                        coroutineScope.launch { onAfterSending(reply) }
                    }
                    override fun onPayloadTransferUpdate(id: String, update: PayloadTransferUpdate) { }
                }
            )
        }
    }

    override fun onConnectionResult(id: String, resolution: ConnectionResolution) {
        coroutineScope.launch {
            when (resolution.status.statusCode) {
                ConnectionsStatusCodes.STATUS_OK -> onReady(id)
                else -> onError()
            }
        }
    }

    override fun onDisconnected(id: String) { }
}

inline fun clientConnectionCallback(
    context: Context,
    coroutineScope: CoroutineScope,
    crossinline onReceive: CoroutineScope.(payload: Payload) -> Unit = {},
    crossinline onError: CoroutineScope.() -> Unit = {}
) = object: ConnectionLifecycleCallback() {
    override fun onConnectionInitiated(id: String, info: ConnectionInfo) {
        coroutineScope.launch {
            acceptConnection(
                context = context,
                id = id,
                callback = object: PayloadCallback() {
                    override fun onPayloadReceived(id: String, payload: Payload) {
                        coroutineScope.launch {
                            onReceive(payload)
                            sendPayload(context, id, Payload.fromBytes("1".toByteArray()))
                        }
                    }

                    override fun onPayloadTransferUpdate(p0: String, p1: PayloadTransferUpdate) { }
                }
            )
        }
    }

    override fun onConnectionResult(id: String, resolution: ConnectionResolution) {
        coroutineScope.launch {
            if (resolution.status.statusCode != ConnectionsStatusCodes.STATUS_OK) {
                onError()
            }
        }
    }

    override fun onDisconnected(p0: String) { }
}