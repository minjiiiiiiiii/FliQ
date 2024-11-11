package com.hongul.filq.data

import kotlinx.coroutines.flow.Flow

interface BusinessCardRepository {
    suspend fun insert(businessCardEntity: BusinessCardEntity)

    suspend fun update(businessCardEntity: BusinessCardEntity)

    suspend fun delete(businessCardEntity: BusinessCardEntity)

    fun get(id: Int, owner: String): Flow<BusinessCardEntity?>

    fun getAllByOwner(owner: String): Flow<List<BusinessCardEntity>>

    fun getAll(): Flow<List<BusinessCardEntity>>
}