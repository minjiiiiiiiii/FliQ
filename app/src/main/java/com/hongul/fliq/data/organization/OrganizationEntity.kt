package com.hongul.fliq.data.organization

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "organizations")
data class OrganizationEntity(
    @PrimaryKey
    val id: Int
)
