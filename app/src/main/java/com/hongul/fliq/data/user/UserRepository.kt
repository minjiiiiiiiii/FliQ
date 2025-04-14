package com.hongul.fliq.data.user

import kotlinx.coroutines.flow.Flow

interface UserRepository {
    suspend fun insert(user: UserEntity)

    suspend fun update(user: UserEntity)

    suspend fun delete(user: UserEntity)

    suspend fun getUserById(id: Long): Flow<UserEntity?>
}