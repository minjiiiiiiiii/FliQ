package com.hongul.filq.data

import com.hongul.filq.model.Avatar
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first

class OfflineBusinessCardRepository(private val dao: BusinessCardDao) : BusinessCardRepository {
    override suspend fun insert(businessCardEntity: BusinessCardEntity) = dao.insert(businessCardEntity)

    override suspend fun update(businessCardEntity: BusinessCardEntity) = dao.update(businessCardEntity)

    override suspend fun updateAvatar(id: Int, avatar: Avatar) {
        val card = getBusinessCard(id).first()
        dao.update(card!!.copy(avatar = avatar))
    }

    override suspend fun delete(businessCardEntity: BusinessCardEntity) = dao.delete(businessCardEntity)

    override fun getBusinessCard(id: Int): Flow<BusinessCardEntity?> = dao.getBusinessCard(id)

    override fun getAllBusinessCard(): Flow<List<BusinessCardEntity>> = dao.getAllBusinessCard()
}