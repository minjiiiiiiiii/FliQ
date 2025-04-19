package com.hongul.fliq.model.card

import androidx.compose.runtime.Immutable

@Immutable
data class Card(
    val id: Long,
    val owner: Long,
    val name: String,
    val email: String = "",
    val contact: String = "",
    val private: Boolean
)
