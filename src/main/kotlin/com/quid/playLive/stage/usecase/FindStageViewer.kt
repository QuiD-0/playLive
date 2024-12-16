package com.quid.playLive.stage.usecase

import com.quid.playLive.stage.gateway.repository.StageViewerRepository
import org.springframework.stereotype.Service

interface FindStageViewer {
    fun byChannel(channel: String): List<String>

    @Service
    class FindStageViewerImpl(
        private val repository: StageViewerRepository
    ) : FindStageViewer {
        override fun byChannel(channel: String): List<String> =
            repository.findCountByChannel(channel)
                .map { it.clientUuid }
    }
}
