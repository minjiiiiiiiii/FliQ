package com.hongul.filq.api

import android.content.Context
import com.google.android.gms.nearby.Nearby
import com.google.android.gms.nearby.connection.AdvertisingOptions
import com.google.android.gms.nearby.connection.ConnectionLifecycleCallback
import com.google.android.gms.nearby.connection.ConnectionOptions
import com.google.android.gms.nearby.connection.DiscoveryOptions
import com.google.android.gms.nearby.connection.EndpointDiscoveryCallback
import com.google.android.gms.nearby.connection.Payload
import com.google.android.gms.nearby.connection.PayloadCallback
import com.google.android.gms.nearby.connection.Strategy
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

object NearbyApi {
    fun CoroutineScope.startAdvertising(
        context: Context,
        name: String,
        callback: ConnectionLifecycleCallback,
        onSuccess: () -> Unit = {},
        onFailure: () -> Unit = {}
    ) = launch(Dispatchers.IO) {
        val option = AdvertisingOptions.Builder()
            .setStrategy(Strategy.P2P_STAR)
            .setLowPower(true)
            .build()

        Nearby.getConnectionsClient(context)
            .startAdvertising(name, SERVICE_ID, callback, option)
            .addOnSuccessListener { onSuccess() }
            .addOnFailureListener { onFailure() }
    }

    fun CoroutineScope.startDiscovering(
        context: Context,
        callback: EndpointDiscoveryCallback,
        onSuccess: () -> Unit = {},
        onFailure: () -> Unit = {}
    ) = launch(Dispatchers.IO) {
        val option = DiscoveryOptions.Builder()
            .setStrategy(Strategy.P2P_STAR)
            .setLowPower(true)
            .build()

        Nearby.getConnectionsClient(context)
            .startDiscovery(SERVICE_ID, callback, option)
            .addOnSuccessListener { onSuccess() }
            .addOnFailureListener { onFailure() }
    }

    fun CoroutineScope.sendPayload(
        context: Context,
        id: String,
        payload: Payload,
        onSuccess: () -> Unit = {},
        onFailure: () -> Unit = {}
    ) = launch(Dispatchers.IO) {
        Nearby.getConnectionsClient(context)
            .sendPayload(id, payload)
            .addOnSuccessListener { onSuccess() }
            .addOnFailureListener { onFailure() }
    }

    fun CoroutineScope.requestConnection(
        context: Context,
        name: String,
        id: String,
        callback: ConnectionLifecycleCallback,
        onSuccess: () -> Unit = {},
        onFailure: () -> Unit = {}
    ) = launch(Dispatchers.IO) {
        val option = ConnectionOptions.Builder()
            .setLowPower(true)
            .build()

        Nearby.getConnectionsClient(context)
            .requestConnection(name, id, callback, option)
            .addOnSuccessListener { onSuccess() }
            .addOnFailureListener { onFailure() }
    }

    fun CoroutineScope.acceptConnection(
        context: Context,
        id: String,
        callback: PayloadCallback,
        onSuccess: () -> Unit = {},
        onFailure: () -> Unit = {}
    ) = launch(Dispatchers.IO) {
        Nearby.getConnectionsClient(context)
            .acceptConnection(id, callback)
            .addOnSuccessListener { onSuccess() }
            .addOnFailureListener { onFailure() }
    }

    fun CoroutineScope.stopAdvertising(context: Context) = launch(Dispatchers.IO) {
        Nearby.getConnectionsClient(context).run {
            stopAdvertising()
            stopAllEndpoints()
        }
    }

    fun CoroutineScope.stopDiscovering(context: Context) = launch(Dispatchers.IO) {
        Nearby.getConnectionsClient(context).stopDiscovery()
    }

    private const val SERVICE_ID = "com.hongul.fliq"
}