package com.hongul.fliq.data.position

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "positions")
data class PositionEntity(
    @PrimaryKey
    val id: Int
)