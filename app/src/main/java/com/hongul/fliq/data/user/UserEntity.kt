package com.hongul.fliq.data.user

import androidx.room.Entity

@Entity(tableName = "users")
data class UserEntity(
    val id: Long,
    val name: String,
    val email: String,
    val profile: String
)