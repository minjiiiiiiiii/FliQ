package com.hongul.filq.data

import com.hongul.filq.model.Avatar
import kotlinx.coroutines.flow.Flow

interface BusinessCardRepository {
    suspend fun insert(businessCardEntity: BusinessCardEntity)

    suspend fun update(businessCardEntity: BusinessCardEntity)

    suspend fun updateAvatar(id: Int, avatar: Avatar)

    suspend fun delete(businessCardEntity: BusinessCardEntity)

    fun getBusinessCard(id: Int): Flow<BusinessCardEntity?>

    fun getAllBusinessCard(): Flow<List<BusinessCardEntity>>
}