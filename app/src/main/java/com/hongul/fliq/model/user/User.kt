package com.hongul.fliq.model.user

import androidx.compose.runtime.Immutable

@Immutable
data class User(
    val id: Int,
    val name: String,
    val email: String = "",
    val profileImageURL: String = ""
)