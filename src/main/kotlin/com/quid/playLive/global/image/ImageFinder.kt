package com.quid.playLive.global.image

import java.io.File

data class ImageFinder(
    private val path: String,
) {
    fun logo(): ByteArray {
        if(path.isEmpty()) return defaultImg(DEFAULT_LOGO)
        return try {
            File(path).readBytes()
        } catch (e: Exception) {
            defaultImg(DEFAULT_LOGO)
        }
    }

    private fun defaultImg(fileName: String): ByteArray {
        return File("src/main/resources/static/asset/$fileName").readBytes()
    }

    companion object {
        const val DEFAULT_LOGO = "logo.png"

    }

}