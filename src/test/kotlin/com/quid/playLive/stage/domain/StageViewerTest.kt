package com.quid.playLive.stage.domain

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class StageViewerTest{

    @Test
    fun addStageViewer(){
        val stageViewer = StageViewer("channel", "uuid")
        assertEquals(1, stageViewer.viewers.size)
    }

    @Test
    fun addMultipleStageViewer(){
        val stageViewer = StageViewer("channel", "uuid")
        stageViewer.add("uuid2")
        assertEquals(2, stageViewer.viewers.size)
    }

    @Test
    fun addDuplicateStageViewer(){
        val stageViewer = StageViewer("channel", "uuid")
        stageViewer.add("uuid")
        stageViewer.add("uuid")
        stageViewer.add("uuid")
        assertEquals(1, stageViewer.viewers.size)
    }
}