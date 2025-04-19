package com.hongul.fliq.data.tag

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tags")
data class TagEntity(
    @PrimaryKey
    val id: Int
)
