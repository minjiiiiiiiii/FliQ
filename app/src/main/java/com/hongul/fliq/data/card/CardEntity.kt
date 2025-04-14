package com.hongul.fliq.data.card

import androidx.room.Entity

@Entity(tableName = "cards")
data class CardEntity(
    val id: Int,
    val owner: Int,
    val name: String,
    val phone: String,
    val email: String,
    val profileImageURL: String,
    val cardImageURL: String,
    val private: Boolean
)