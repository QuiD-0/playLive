package com.quid.playLive.stage.domain

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import java.lang.Thread.sleep

class ViewerTest{

    @Test
    fun sameViewer(){
        val viewer1 = Viewer("uuid")
        sleep(1)
        val viewer2 = Viewer("uuid")
        assertEquals(viewer1, viewer2)
        assertNotSame(viewer1.datetime, viewer2.datetime)
    }
}