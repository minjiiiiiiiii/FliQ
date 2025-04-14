package com.hongul.fliq.data.user.impl

import com.hongul.fliq.data.user.UserDao
import com.hongul.fliq.data.user.UserEntity
import com.hongul.fliq.data.user.UserRepository
import kotlinx.coroutines.flow.Flow

class InternalUserRepository(
    private val dao: UserDao
): UserRepository {
    override suspend fun insert(user: UserEntity) = dao.insert(user)

    override suspend fun update(user: UserEntity) = dao.update(user)

    override suspend fun delete(user: UserEntity) = dao.delete(user)

    override suspend fun getUserById(id: Long): Flow<UserEntity?> = dao.getUserById(id)
}