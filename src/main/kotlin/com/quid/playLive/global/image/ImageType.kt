package com.quid.playLive.global.image

enum class ImageType {
    AVATAR, THUMBNAIL, WAITING_CURTAIN;

    fun default() = when (this) {
        AVATAR -> "$ASSET/avatar.png"
        THUMBNAIL -> "$ASSET/thumbnail.png"
        WAITING_CURTAIN -> "$ASSET/waiting_curtain.png"
    }

    companion object {
        const val ASSET = "src/main/resources/static/asset/"
    }
}