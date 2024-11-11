package com.hongul.filq.data

import androidx.compose.ui.graphics.Color
import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.hongul.filq.model.Avatar
import com.hongul.filq.model.SNS
import com.hongul.filq.model.Sticker

@ProvidedTypeConverter
object SNSConverter {
    @TypeConverter
    fun fromSNSList(sns: List<SNS>): String {
        return sns.joinToString(",") {
            val type = when (it) {
                is SNS.Facebook -> 0
                is SNS.Instagram -> 1
                is SNS.X -> 2
                is SNS.Youtube -> 3
            }
            "$type;${it.link}"
        }
    }

    @TypeConverter
    fun toSNSList(sns: String): List<SNS> {
        return sns.split(",").map {
            val (type, link) = sns.split(";")
            when (type.toInt()) {
                0 -> SNS.Facebook(link)
                1 -> SNS.Instagram(link)
                2 -> SNS.X(link)
                3 -> SNS.Youtube(link)
                else -> throw IllegalArgumentException("Unknown SNS type: $type")
            }
        }
    }
}

@ProvidedTypeConverter
object AvatarConverter {
    @TypeConverter
    fun fromAvatar(avatar: Avatar): String {
        return "${avatar.path};${fromSticker(avatar.sticker)}"
    }

    @TypeConverter
    fun toAvatar(avatar: String): Avatar {
        val (path, sticker) = avatar.split(";")
        return Avatar(
            path = deserializeEmpty(path) { it },
            sticker = deserializeEmpty(sticker, { toSticker(it) })
        )
    }

    @TypeConverter
    fun fromSticker(sticker: Sticker?): String {
        return sticker?.let { "${it.pos}:${it.color.value}" } ?: ""
    }

    @TypeConverter
    fun toSticker(sticker: String): Sticker? {
        return sticker.ifEmpty { null }?.let {
            val (pos, color) = it.split(":")
            Sticker(pos.toInt(), Color(color.toULong()))
        }
    }

    private fun <T> deserializeEmpty(value: String, deserializer: (String) -> T): T? {
        return value.ifEmpty { null }?.let(deserializer)
    }
}