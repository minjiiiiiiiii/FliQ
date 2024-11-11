package com.hongul.filq.data

import kotlinx.coroutines.flow.Flow

class OfflineBusinessCardRepository(private val dao: BusinessCardDao) : BusinessCardRepository {
    override suspend fun insert(businessCardEntity: BusinessCardEntity) = dao.insert(businessCardEntity)

    override suspend fun update(businessCardEntity: BusinessCardEntity) = dao.update(businessCardEntity)

    override suspend fun delete(businessCardEntity: BusinessCardEntity) = dao.delete(businessCardEntity)

    override fun get(id: Int, owner: String): Flow<BusinessCardEntity?> = dao.getBusinessCard(id, owner)

    override fun getAllByOwner(owner: String): Flow<List<BusinessCardEntity>> = dao.getAllBusinessCardByOwner(owner)

    override fun getAll(): Flow<List<BusinessCardEntity>> = dao.getAllBusinessCard()
}