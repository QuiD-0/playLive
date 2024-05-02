package com.quid.playLive.global.image

import com.quid.playLive.global.image.ImageType.*
import java.io.File

@FunctionalInterface
sealed interface Image {
    val path: String
    val type: ImageType

    fun toImage(): ByteArray =
        if (File(path).exists()) {
            File(path)
        } else {
            File(type.default())
        }.readBytes()
}

data class Avatar(
    override val path: String = "",
) : Image {
    override val type: ImageType = AVATAR
}

data class Thumbnail(
    override val path: String = "",
) : Image {
    override val type: ImageType = THUMBNAIL
}

data class WaitingCurtain(
    override val path: String = "",
) : Image {
    override val type: ImageType = WAITING_CURTAIN
}

