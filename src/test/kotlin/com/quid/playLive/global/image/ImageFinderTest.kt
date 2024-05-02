package com.quid.playLive.global.image

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class ImageFinderTest{

    @Test
    fun pathToImageByte(){
        val path = "src/main/resources/static/asset/logo.png"
        val imageFinder = ImageFinder(path)
        assertDoesNotThrow { imageFinder.logo() }
    }
}