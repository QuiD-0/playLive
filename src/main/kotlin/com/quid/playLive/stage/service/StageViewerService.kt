package com.quid.playLive.stage.service

import com.quid.playLive.stage.domain.StageViewer
import com.quid.playLive.stage.gateway.repository.StageViewerRepository
import org.springframework.stereotype.Service

interface StageViewerService {
    fun add(channel: String, uuid: String)
    fun byChannel(channel: String): List<String>

    @Service
    class StageViewerServiceImpl(
        private val repository: StageViewerRepository
    ): StageViewerService {
        override fun add(channel: String, uuid: String): Unit =
              repository.save(StageViewer(channel, uuid))

        override fun byChannel(channel: String): List<String> =
            repository.findCountByChannel(channel)
                .map { it.clientUuid }
    }
}
