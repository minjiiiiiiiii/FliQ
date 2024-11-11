package com.hongul.filq.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface BusinessCardDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(businessCardEntity: BusinessCardEntity)

    @Update
    suspend fun update(businessCardEntity: BusinessCardEntity)

    @Delete
    suspend fun delete(businessCardEntity: BusinessCardEntity)

    @Query("SELECT * from business_card WHERE id = :id AND owner = :owner")
    fun getBusinessCard(id: Int, owner: String): Flow<BusinessCardEntity>

    @Query("SELECT * from business_card WHERE owner = :owner")
    fun getAllBusinessCardByOwner(owner: String): Flow<List<BusinessCardEntity>>
}