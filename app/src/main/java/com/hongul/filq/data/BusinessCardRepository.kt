package com.hongul.filq.data

import kotlinx.coroutines.flow.Flow

interface BusinessCardRepository {
    suspend fun insert(businessCardEntity: BusinessCardEntity)

    suspend fun update(businessCardEntity: BusinessCardEntity)

    suspend fun delete(businessCardEntity: BusinessCardEntity)

    fun getBusinessCard(id: Int): Flow<BusinessCardEntity?>

    fun getAllBusinessCard(): Flow<List<BusinessCardEntity>>
}