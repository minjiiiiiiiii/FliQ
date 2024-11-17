package com.hongul.filq.model

import android.util.Log
import androidx.compose.ui.graphics.Color
import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.descriptors.buildClassSerialDescriptor
import kotlinx.serialization.descriptors.element
import kotlinx.serialization.encoding.CompositeDecoder
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.encoding.decodeStructure
import kotlinx.serialization.encoding.encodeStructure

object SNSSerializer: KSerializer<SNS> {
    override val descriptor: SerialDescriptor
        = buildClassSerialDescriptor("SNS") {
            element<Int>("type")
            element<String>("link")
        }

    override fun deserialize(decoder: Decoder): SNS =
        decoder.decodeStructure(descriptor) {
            var type: Int = -1
            lateinit var link: String

            while (true) {
                when (val index = decodeElementIndex(descriptor)) {
                    0 -> type = decodeIntElement(descriptor, 0)
                    1 -> link = decodeStringElement(descriptor, 1)
                    CompositeDecoder.DECODE_DONE -> break
                    else -> error("Unexpected index: $index")
                }
            }

            when (type) {
                0 -> SNS.Facebook(link)
                1 -> SNS.Instagram(link)
                2 -> SNS.X(link)
                3 -> SNS.Youtube(link)
                else -> error("Unexpected type: $type")
            }
        }

    override fun serialize(encoder: Encoder, value: SNS) {
        val type = when (value) {
            is SNS.Facebook -> 0
            is SNS.Instagram -> 1
            is SNS.X -> 2
            is SNS.Youtube -> 3
        }

        encoder.encodeStructure(descriptor) {
            encodeIntElement(descriptor, 0, type)
            encodeStringElement(descriptor, 1, value.link)
        }
    }
}

object ColorSerializer: KSerializer<Color> {
    override val descriptor: SerialDescriptor =
        PrimitiveSerialDescriptor("androidx.compose.ui.graphics.Color", PrimitiveKind.LONG)

    override fun deserialize(decoder: Decoder): Color {
        return Color(decoder.decodeLong().toULong())
    }

    override fun serialize(encoder: Encoder, value: Color) {
        encoder.encodeLong(value.value.toLong())
    }
}