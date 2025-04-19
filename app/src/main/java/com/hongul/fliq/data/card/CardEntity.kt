package com.hongul.fliq.data.card

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cards")
data class CardEntity(
    @PrimaryKey
    val id: Int,
    val owner: Int,
    val name: String,
    val phone: String,
    val email: String,
    val profileImageURL: String,
    val cardImageURL: String,
    val private: Boolean
)