package com.hongul.filq.data

import androidx.compose.ui.graphics.Color
import androidx.room.TypeConverter
import com.hongul.filq.model.Avatar
import com.hongul.filq.model.SNS
import com.hongul.filq.model.Sticker

object SNSConverter {
    @TypeConverter
    fun fromSNS(sns: SNS): String {
        val type = when (sns) {
            is SNS.Facebook -> 0
            is SNS.Instagram -> 1
            is SNS.X -> 2
            is SNS.Youtube -> 3
        }
        return "$type;${sns.link}"
    }

    @TypeConverter
    fun toSNS(sns: String): SNS {
        val (type, link) = sns.split(";")
        return when (type.toInt()) {
            0 -> SNS.Facebook(link)
            1 -> SNS.Instagram(link)
            2 -> SNS.X(link)
            3 -> SNS.Youtube(link)
            else -> throw IllegalArgumentException("Unknown SNS type: $type")
        }
    }
}

object AvatarConverter {
    @TypeConverter
    fun fromAvatar(avatar: Avatar): String {
        val sticker = avatar.sticker?.let { "${it.pos}:${it.color.value}" } ?: ""
        return "${avatar.path};$sticker"
    }

    @TypeConverter
    fun toAvatar(avatar: String): Avatar {
        val (path, sticker) = avatar.split(";")
        return Avatar(
            path = deserializeEmpty(path) { it },
            sticker = deserializeEmpty(sticker) {
                val (pos, color) = it.split(":")
                Sticker(pos.toInt(), Color(color.toULong()))
            }
        )
    }

    private fun <T> deserializeEmpty(value: String, deserializer: (String) -> T): T? {
        return value.ifEmpty { null }?.let(deserializer)
    }
}