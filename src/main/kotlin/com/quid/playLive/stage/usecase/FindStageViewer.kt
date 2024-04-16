package com.quid.playLive.stage.usecase

import com.quid.playLive.stage.domain.Viewer
import com.quid.playLive.stage.gateway.repository.StageViewerRepository
import org.springframework.stereotype.Service

interface FindStageViewer {
    fun byChannel(channel: String): List<Viewer>

    @Service
    class FindStageViewerImpl(
        private val repository: StageViewerRepository
    ) : FindStageViewer {
        override fun byChannel(channel: String): List<Viewer> =
            repository.findByChannel(channel).viewers.toList()
    }
}