package com.quid.playLive.global.image

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class ImageTest {

    @Test
    fun imageWillReturnByteArray() {
        val image = Avatar("src/test/resources/logo.png")

        val result = image.toImage()

        assert(result.isNotEmpty())
    }

    @Test
    fun emptyImageWillReturnDefaultImage() {
        val emptyImage = Avatar().toImage()

        val defaultImage = Avatar("src/main/resources/static/asset/avatar.png").toImage()

        assertEquals(emptyImage.size, defaultImage.size)
    }
}