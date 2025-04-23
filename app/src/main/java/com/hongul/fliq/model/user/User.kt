package com.hongul.fliq.model.user

import androidx.compose.runtime.Immutable
import com.hongul.fliq.data.user.UserEntity

@Immutable
data class User(
    val id: Int,
    val name: String,
    val email: String = "",
    val profileImageURL: String = ""
) {
    companion object {
        fun fromEntity(entity: UserEntity) = User(
            id = entity.id,
            name = entity.name,
            email = entity.email,
            profileImageURL = entity.profileImageURL
        )
    }
}

fun User.toEntity() = UserEntity(
    id = id,
    name = name,
    email = email,
    profileImageURL = profileImageURL
)