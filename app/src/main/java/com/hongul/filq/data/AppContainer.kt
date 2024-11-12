package com.hongul.filq.data

import android.content.Context

interface AppContainer {
    val businessCardRepository: BusinessCardRepository
}

class AppDataContainer(private val context: Context) : AppContainer {
    override val businessCardRepository by lazy {
        OfflineBusinessCardRepository(FliQDatabase.getDatabase(context).businessCardDao())
    }
}