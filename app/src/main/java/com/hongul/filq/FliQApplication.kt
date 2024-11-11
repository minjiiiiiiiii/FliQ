package com.hongul.filq

import android.app.Application
import com.hongul.filq.data.AppContainer
import com.hongul.filq.data.AppDataContainer

class FliQApplication : Application() {
    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = AppDataContainer(this)
    }
}