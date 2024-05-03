package com.quid.playLive.global.image

import org.springframework.core.io.DefaultResourceLoader
import java.io.File


@FunctionalInterface
sealed interface Image {
    val user: String
    val path: String

    fun toImage(): ByteArray =
        if (File(path).exists()) {
            File(path).readBytes()
        } else {
            byteArrayOf()
        }
}

data class Avatar(
    override val user: String = "",
) : Image {
    override val path: String = if(user == "") "" else "/home/ubuntu/avatars/$user.jpg"
}

data class Thumbnail(
    override val user: String = "",
) : Image {
    override val path: String = if(user == "") "" else "/home/ubuntu/thumbnails/$user.jpg"
}

data class WaitingCurtain(
    override val user: String = "",
) : Image {
    override val path: String = if(user == "") "" else "/home/ubuntu/waitingCurtain/$user.jpg"
}

