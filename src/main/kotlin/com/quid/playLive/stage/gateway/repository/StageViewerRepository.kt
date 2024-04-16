package com.quid.playLive.stage.gateway.repository

import com.quid.playLive.stage.domain.StageViewer
import com.quid.playLive.stage.domain.Viewer
import org.springframework.stereotype.Repository

interface StageViewerRepository {
    fun findByChannel(channel: String): StageViewer
    fun merge(it: StageViewer)

    @Repository
    class InMemoryStageViewerRepository : StageViewerRepository {
        private val memory = mutableMapOf<String, MutableSet<Viewer>>()

        override fun findByChannel(channel: String): StageViewer =
            memory.getOrDefault(channel, mutableSetOf())
                .run { StageViewer(channel, this) }

        override fun merge(it: StageViewer) = memory.set(it.channel, it.viewers)
    }
}