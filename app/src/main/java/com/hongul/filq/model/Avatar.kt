package com.hongul.filq.model

data class Avatar(
    val path: String?,
    val sticker: Sticker?
) {
    val isImage: Boolean
        get() = path != null
    val isSticker: Boolean
        get() = sticker != null
    val isDefault: Boolean
        get() = !isImage && !isSticker
}

data class Sticker(
    val pos: Int,
    val color: ULong
) {
    companion object {
        const val SIZE = 256
    }

    val size: Int get() = SIZE
    val x: Int get() = (pos % 4) * size
    val y: Int get() = (pos / 4) * size
}