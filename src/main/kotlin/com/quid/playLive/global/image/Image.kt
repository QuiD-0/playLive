package com.quid.playLive.global.image

import com.quid.playLive.global.image.ImageType.*
import java.io.File

@FunctionalInterface
sealed interface Image {
    val user: String
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
    override val user: String = "",
) : Image {
    override val path: String = if(user == "") "" else "/home/ubuntu/avatars/$user.jpg"
    override val type: ImageType = AVATAR
}

data class Thumbnail(
    override val user: String = "",
) : Image {
    override val path: String = if(user == "") "" else "/home/ubuntu/thumbnails/$user.jpg"
    override val type: ImageType = THUMBNAIL
}

data class WaitingCurtain(
    override val user: String = "",
) : Image {
    override val path: String = if(user == "") "" else "/home/ubuntu/waitingCurtain/$user.jpg"
    override val type: ImageType = WAITING_CURTAIN
}

