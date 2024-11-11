package com.hongul.filq.data

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.hongul.filq.model.Avatar
import com.hongul.filq.model.SNS

@Entity(
    tableName = "business_card",
    primaryKeys = ["id", "owner"]
)
data class BusinessCardEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val owner: String,

    val title: String,
    @ColumnInfo(name = "phone_number")
    val phoneNumber: String,
    val email: String,
    val address: String,
    val organization: String,
    val department: String,
    val position: String,
    @Embedded val sns: List<SNS>,

    @ColumnInfo(name = "image_path") val imagePath: String,
    @Embedded val avatar: Avatar,
    val introduction: String,

    val memo: String
)
