package com.hongul.fliq.model.card

import androidx.compose.runtime.Immutable

@Immutable
data class Card(
    val id: Int,
    val owner: Int,
    val name: String = "",
    val email: String = "",
    val contact: String = "",
    val profileImageURL: String,
    val cardImageURL: String,
    val organization: String,
    val position: String,
    val private: Boolean
)
