package com.hongul.filq.api

import android.content.Context
import com.google.android.gms.nearby.Nearby
import com.google.android.gms.nearby.connection.AdvertisingOptions
import com.google.android.gms.nearby.connection.ConnectionLifecycleCallback
import com.google.android.gms.nearby.connection.DiscoveryOptions
import com.google.android.gms.nearby.connection.EndpointDiscoveryCallback
import com.google.android.gms.nearby.connection.Strategy
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async

object NearbyApi {
    fun CoroutineScope.startAdvertising(context: Context, name: String, callback: ConnectionLifecycleCallback) = async(Dispatchers.IO) {
        val option = AdvertisingOptions.Builder()
            .setStrategy(Strategy.P2P_STAR)
            .build()

        Nearby.getConnectionsClient(context)
            .startAdvertising(name, SERVICE_ID, callback, option)
    }

    fun CoroutineScope.startDiscovering(context: Context, callback: EndpointDiscoveryCallback) = async(Dispatchers.IO) {
        val option = DiscoveryOptions.Builder()
            .setStrategy(Strategy.P2P_STAR)
            .build()

        Nearby.getConnectionsClient(context)
            .startDiscovery(SERVICE_ID, callback, option)
    }

    private const val SERVICE_ID = "com.hongul.fliq"
}