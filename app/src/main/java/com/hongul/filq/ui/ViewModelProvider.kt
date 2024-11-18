package com.hongul.filq.ui

import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.hongul.filq.FliQApplication
import com.hongul.filq.ui.home.HomeViewModel
import com.hongul.filq.ui.share.CardShareViewModel

object HomeViewModelProvider {
    val Factory = viewModelFactory {
        initializer {
            HomeViewModel(application().container.businessCardRepository)
        }
    }
}

object CardShareViewModelProvider {
    val Factory = viewModelFactory {

    }

    fun factory(cardId: Int) = viewModelFactory {
        initializer {
            CardShareViewModel(
                cardId,
                application().container.businessCardRepository
            )
        }
    }
}

fun CreationExtras.application(): FliQApplication =
    (this[APPLICATION_KEY] as FliQApplication)