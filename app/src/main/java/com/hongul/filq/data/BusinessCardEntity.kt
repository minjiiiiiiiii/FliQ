package com.hongul.filq.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.hongul.filq.model.Avatar
import com.hongul.filq.model.SNS

@Entity(
    tableName = "business_card",
)
data class BusinessCardEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    val name: String,
    val title: String,
    @ColumnInfo(name = "phone_number")
    val phoneNumber: String,
    val email: String,
    val address: String,
    val organization: String,
    val department: String,
    val position: String,
    val sns: List<SNS>,

    @ColumnInfo(name = "image_path")
    val imagePath: String,
    val avatar: Avatar,
    val introduction: String
)
