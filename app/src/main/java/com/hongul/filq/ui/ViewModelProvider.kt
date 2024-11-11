package com.hongul.filq.ui

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.hongul.filq.FliQApplication
import com.hongul.filq.ui.home.HomeViewModel

object HomeViewModelProvider {
    val Factory = viewModelFactory {
        initializer {
            HomeViewModel(application().container.businessCardRepository)
        }
    }
}

fun CreationExtras.application(): FliQApplication =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as FliQApplication)